package com.axiante.mui.webapp.business.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.MuiContext;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.data.UserDTO;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.inject.Instance;
import javax.persistence.NoResultException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UsersEntity adminUser;

    @Mock
    private RolesEntity adminRole;

    @Mock
    private UsersEntity userNoGroup;

    @Mock
    private RolesEntity fooRole;

    @Mock
    private UsersEntity userWithGroup;

    @Mock
    private UsersEntity userWithTwoGroups;

    @Mock
    private GroupEntity group1;

    @Mock
    private GroupEntity group2;

    @Mock
    private CanalePromozioneService canalePromozioneService;

    @Mock
    private Instance<MuiService> muiServiceInstance;

    @Mock
    private MuiService muiService;

    @InjectMocks
    private UserServiceImpl service;

    @Before
    public void setUp() {
        when(adminUser.isAdmin()).thenReturn(true);
        when(userNoGroup.getGruppi()).thenReturn(null);
        when(userWithGroup.getGruppi()).thenReturn(new HashSet<>(Collections.singletonList(group1)));
        lenient().when(userWithGroup.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(fooRole)));
        lenient().when(userWithTwoGroups.getGruppi()).thenReturn(new HashSet<>(Arrays.asList(group1, group2)));
        lenient().when(userWithTwoGroups.getRoles()).thenReturn(new HashSet<>(Collections.singletonList(fooRole)));
        final Set<CanaleEntity> channelsInGroup = IntStream.rangeClosed(1, 3).mapToObj(this::mockChannel)
                .collect(Collectors.toSet());
        when(group1.getCanali()).thenReturn(channelsInGroup);
        lenient().when(group1.getCodiceGruppo()).thenReturn("GRP");
        lenient().when(group2.getCodiceGruppo()).thenReturn("GR2");
        final List<CanalePromozioneEntity> channelsPromo = IntStream.rangeClosed(1, 3).mapToObj(this::mockChannelPromo)
                .collect(Collectors.toList());
        when(canalePromozioneService.findByCodiciCanale(anyList())).thenReturn(channelsPromo);
        when(muiServiceInstance.get()).thenReturn(muiService);
    }

    @Test(expected = NullPointerException.class)
    public void asDto_givenNullEntity_shouldThrowException() {
        service.asDto(null, null);
    }

    @Test
    public void asDto_givenAdminUser_shouldSetNullChannels() {
        final UserDTO userDTO = service.asDto(adminUser, null);
        assertNotNull(userDTO);
        assertNull(userDTO.getCanali());
    }

    @Test
    public void asDto_givenUserWithoutGroup_shouldSetEmptyChannelsList() {
        final UserDTO userDTO = service.asDto(userNoGroup, null);
        assertNotNull(userDTO);
        assertTrue(userDTO.getCanali().isEmpty());
    }

    @Test
    public void asDto_givenUserWithGroup_shouldSetChannelsList() {
        final UserDTO userDTO = service.asDto(userWithGroup, null);
        assertNotNull(userDTO);
        final List<CanalePromozioneEntity> channels = userDTO.getCanali();
        assertEquals(3, channels.size());
        final List<Long> codes = channels.stream().map(CanalePromozioneEntity::getCodiceCanale).sorted()
                .collect(Collectors.toList());
        assertEquals(1, (long) codes.get(0));
        assertEquals(2, (long) codes.get(1));
        assertEquals(3, (long) codes.get(2));
    }

    @Test
    public void asDtoWithContext_shouldSetEmptyChannelsList_whenContextNotInDb() {
        when(muiService.findContextByCode("FOO")).thenThrow(NoResultException.class);
        UserDTO userDTO = service.asDto(userWithGroup, "FOO");
        assertTrue(userDTO.getCanali().isEmpty());
        userDTO = service.asDto(adminUser, "FOO");
        assertTrue(userDTO.getCanali().isEmpty());
        verify(canalePromozioneService, never()).findByCodiciCanale(anyList());
    }

    @Test
    public void asDtoWithContext_shouldSetEmptyChannelsList_whenContextHasNotChannels() {
        final MuiContext muiContext = mock(MuiContext.class);
        when(muiContext.getCanali()).thenReturn(Collections.emptySet());
        when(muiService.findContextByCode("FOO")).thenReturn(muiContext);
        UserDTO userDTO = service.asDto(userWithGroup, "FOO");
        assertTrue(userDTO.getCanali().isEmpty());
        userDTO = service.asDto(adminUser, "FOO");
        assertTrue(userDTO.getCanali().isEmpty());
        verify(canalePromozioneService, never()).findByCodiciCanale(anyList());
    }

    @Test
    public void asDtoWithContext_shouldSetOnlyChannelsInContext_whenContextHasChannels() {
        final UserDTO userDTO = service.asDto(userWithGroup, null);
        assertNotNull(userDTO);
        final List<CanalePromozioneEntity> channels = userDTO.getCanali();
        assertEquals(3, channels.size());
        final List<Long> codes = channels.stream().map(CanalePromozioneEntity::getCodiceCanale).sorted()
                .collect(Collectors.toList());
        assertEquals(1, (long) codes.get(0));
        assertEquals(2, (long) codes.get(1));
        assertEquals(3, (long) codes.get(2));
    }

    @Test
    public void asDtoWithContext_givenNonAdminAndNullContext_shouldSetChannelsSameAsFromGroup() {
        final UserDTO userDTO = service.asDto(userWithGroup, null);
        assertNotNull(userDTO);
        final List<CanalePromozioneEntity> channels = userDTO.getCanali();
        assertEquals(3, channels.size());
        final List<Long> codes = channels.stream().map(CanalePromozioneEntity::getCodiceCanale).sorted()
                .collect(Collectors.toList());
        assertEquals(1, (long) codes.get(0));
        assertEquals(2, (long) codes.get(1));
        assertEquals(3, (long) codes.get(2));
    }

    @Test
    public void asDtoCreateFlag_givenAdminUser_shouldSetOwnershipOnAllChannels() {
        final UserDTO userDTO = service.asDto(adminUser, null);
        assertNotNull(userDTO);
        assertNull(userDTO.getCanaliCreatePromo());
    }

    @Test
    public void asDtoCreateFlag_givenNonAdminUserWithoutGroup_shouldSetEmptyOwnership() {
        final UserDTO userDTO = service.asDto(userNoGroup, null);
        assertNotNull(userDTO);
        assertTrue(userDTO.getCanaliCreatePromo().isEmpty());
    }

    @Test
    public void asDtoCreateFlag_givenNonAdminUserWithGroup_shouldSetOwnership() {
        when(muiService.findOwnershipCodiciCanaleByGruppi(Collections.singleton(group1)))
                .thenReturn(Collections.singletonList(1L));
        final UserDTO userDTO = service.asDto(userWithGroup, null);
        assertNotNull(userDTO);
        assertEquals(1, userDTO.getCanaliCreatePromo().size());
        assertEquals(1, (long) userDTO.getCanaliCreatePromo().get(0));
    }

    @Test
    public void asDtoCanaliOwnership_givenAdminUser_shouldSetCanaliOwnershipToNull() {
        final UserDTO userDTO = service.asDto(adminUser, null);
        assertNotNull(userDTO);
        assertNull(userDTO.getCanaliCreatePromo());
    }

    @Test
    public void asDtoCanaliOwnership_givenUserWithoutGroup_shouldSetCanaliOwnershipToEmpty() {
        final UserDTO userDTO = service.asDto(userNoGroup, null);
        assertNotNull(userDTO);
        assertTrue(userDTO.getCanaliCreatePromo().isEmpty());
    }

    @Test
    public void asDtoCanaliOwnership_givenOwnerFlagTrue_shouldCanaliOwnershipContainsCodiceCanale() {
        when(muiService.findOwnershipCodiciCanaleByGruppi(Collections.singleton(group1)))
                .thenReturn(Collections.singletonList(1L));
        final UserDTO userDTO = service.asDto(userWithGroup, null);
        assertNotNull(userDTO);
        assertEquals(1, userDTO.getCanaliCreatePromo().size());
        assertEquals(1, (long) userDTO.getCanaliCreatePromo().iterator().next());
    }

    private CanaleEntity mockChannel(long code) {
        final CanaleEntity mock = mock(CanaleEntity.class);
        when(mock.getCodiceCanale()).thenReturn(code);
        return mock;
    }

    private CanalePromozioneEntity mockChannelPromo(long code) {
        final CanalePromozioneEntity mock = mock(CanalePromozioneEntity.class);
        when(mock.getCodiceCanale()).thenReturn(code);
        return mock;
    }
}