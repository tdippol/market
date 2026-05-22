// language: java
    package com.axiante.mui.dbpromo.persistence.dto;

    import lombok.AccessLevel;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import java.util.Date;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class MacrospazioWithEventsDTO {
        private Long id;
        private String codice;
        private String descrizione;
        private Date dataInizio;
        private Date dataFine;
        private Date dataInserimento;
        private String codiceUtenteInserimento;
        private Date dataAggiornamento;
        private String codiceUtenteAggiornamento;
        private Long eventsCount;
        private Double listino;

        public Boolean getHasEvents() {
            return eventsCount != null && eventsCount > 0;
        }

        // This constructor is needed because Oracle is such an hasshole that
        // it retuns BigInteger/BigDecimal on counts. This permits JPQL/EclipseLink
        // to pass BigInteger/BigDecimal (Number).
        // FYI: PostgreSQL OpenEjb returns Long/Double so no problem there
        // (and it's free as in free beer) .
        public MacrospazioWithEventsDTO(Long id,
                                        String codice,
                                        String descrizione,
                                        Date dataInizio,
                                        Date dataFine,
                                        Date dataInserimento,
                                        String codiceUtenteInserimento,
                                        Date dataAggiornamento,
                                        String codiceUtenteAggiornamento,
                                        Number eventsCount,
                                        Number listino) {
            this.id = id;
            this.codice = codice;
            this.descrizione = descrizione;
            this.dataInizio = dataInizio;
            this.dataFine = dataFine;
            this.dataInserimento = dataInserimento;
            this.codiceUtenteInserimento = codiceUtenteInserimento;
            this.dataAggiornamento = dataAggiornamento;
            this.codiceUtenteAggiornamento = codiceUtenteAggiornamento;
            this.eventsCount = (eventsCount == null) ? null : eventsCount.longValue();
            this.listino = (listino == null) ? null : listino.doubleValue();
        }
    }