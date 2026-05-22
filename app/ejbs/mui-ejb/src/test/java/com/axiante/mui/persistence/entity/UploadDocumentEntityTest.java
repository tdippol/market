package com.axiante.mui.persistence.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UploadDocumentEntityTest {

    @Test
    public void addRole_givenValidRole_shouldAddIt() {
        final UploadDocumentEntity document = createDocumentEntity(1, "foo.txt");
        final RolesEntity role = createRoleEntity(1, "foo role");
        assertNull(document.getRoles());
        document.addRole(role);
        assertEquals(1, document.getRoles().size());
    }

    @Test
    public void removeRole_givenValidRole_shouldRemoveIt() {
        final UploadDocumentEntity document = createDocumentEntity(2, "bar.txt");
        final RolesEntity role = createRoleEntity(2, "bar role");
        document.addRole(role);
        assertEquals(1, document.getRoles().size());
        document.removeRole(role);
        assertTrue(document.getRoles().isEmpty());
    }

    @Test
    public void removeRole_givenInvalidRole_shouldNotRemoveIt() {
        final UploadDocumentEntity document = createDocumentEntity(2, "bar.txt");
        final RolesEntity role1 = createRoleEntity(1, "bar role");
        final RolesEntity role2 = createRoleEntity(2, "baz role");
        document.addRole(role1);
        assertEquals(1, document.getRoles().size());
        document.removeRole(role2);
        assertEquals(1, document.getRoles().size());
    }

    private UploadDocumentEntity createDocumentEntity(Integer id, String name) {
        final UploadDocumentEntity entity = new UploadDocumentEntity();
        entity.setId(1);
        entity.setName("foo.txt");
        return entity;
    }

    private RolesEntity createRoleEntity(Integer id, String name) {
        final RolesEntity entity = new RolesEntity();
        entity.setId(id);
        entity.setName(name);
        return entity;
    }
}
