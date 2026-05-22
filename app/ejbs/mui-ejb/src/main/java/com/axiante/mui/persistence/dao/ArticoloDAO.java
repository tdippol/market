package com.axiante.mui.persistence.dao;

import java.util.List;

public interface ArticoloDAO {
    Long countByArticoloIdAndCodiciGruppo(Long idArticolo, List<String> codiciGruppo);
    Long countWritableByArticoloIdAndCodiciGruppo(Long idArticolo, List<String> codiciGruppo);
    List<String> findCompratoriByIdArticoliAndCodiciGruppo(List<Long> idArticoli, List<String> codiciGruppo);
    List<String> findWritableCompratoriByIdArticoliAndCodiciGruppo(List<Long> idArticoli, List<String> codiciGruppo);
}
