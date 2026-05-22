package com.axiante.mui.dbpromo.business.service;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.service.impl.data.ItemUpload;
import com.axiante.mui.dbpromo.business.service.impl.data.ShopItemUpload;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UploadExcelService {

    Set<String> readFile(ElementType elementType, File file) throws IOException;

    boolean validate(ElementType elementType, String code);

    List<ItemUpload> readFileUntilEmptyRow(ElementType elementType, File file, Long buyerId, Integer maxRows) throws IOException;

    ItemUpload validateUpload(ElementType elementType, String code, Long buyerId);

    Set<ShopItemUpload> readFileUntilEmptyRow(File file, Set<String> promoNegoziSigles, Integer maxRows) throws IOException;
}
