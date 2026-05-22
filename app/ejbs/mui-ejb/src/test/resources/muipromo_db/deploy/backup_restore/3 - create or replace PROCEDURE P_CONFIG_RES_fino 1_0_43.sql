create or replace PROCEDURE P_CONFIG_RESTORE (v_BACKUP_ID NUMBER , v_TABLE_NAME  VARCHAR2 ) AS
v_RESTORED_DATE DATE ; 
v_SAFE_BACKUP_ID NUMBER ; 
v_BACKUP_ID_RESTORED NUMBER ; 
v_PROCEDI NUMBER ;
BEGIN
  v_PROCEDI := 0 ; 
  v_BACKUP_ID_RESTORED := v_BACKUP_ID ; 
  v_RESTORED_DATE := SYSDATE ; 
  
/* ESEGUO UN BACKUP DI SICUREZZA (SAFE_BACKUP_ID) */ 
P_CONFIG_BACKUP() ;  
SELECT MAX(BACKUP_ID ) into v_SAFE_BACKUP_ID  FROM BACKUP_LOG ; 


/* VERIFICO CHE ESISTA IL BALCKUP (SE v_TABLE_NAME è null tutto se no sulla specifica tabella )*/
 
IF v_TABLE_NAME is null THEN 
      v_PROCEDI := 0 ; 
      
ELSIF v_TABLE_NAME = '*' THEN
      select count(*) into  v_PROCEDI from BACKUP_LOG WHERE BACKUP_ID = v_BACKUP_ID_RESTORED ; 
ELSE 
      select count(*) into  v_PROCEDI from BACKUP_LOG WHERE BACKUP_ID = v_BACKUP_ID_RESTORED AND TABLE_NAME = v_TABLE_NAME;
END IF; 


IF v_PROCEDI > 0 THEN 


   /* METTO DA PARTE I CONSTRAINT DELLE TABELLE PER abilitazione / disabilitazione  */ 
   delete from RESTORE_TMP_CONSTRAINTS ; 
   insert into RESTORE_TMP_CONSTRAINTS( OWNER , TABLE_NAME , CONSTRAINT_NAME , CONSTRAINT_TYPE  , STATUS)  
   SELECT c.owner, c.table_name, c.constraint_name , c.constraint_type , c.status 
   FROM user_constraints c, user_tables t
   WHERE c.table_name = t.table_name
   --AND c.status = 'ENABLED'
   AND c.owner = 'MUIPROMO'
   AND NOT (t.iot_type IS NOT NULL AND c.constraint_type = 'P')
   ORDER BY c.constraint_type DESC ; 

  /* DIABILITO TEMPORANEAMENTE I CONSTRAINT ENABLED NELLE TABELLE */ 
  FOR c IN
  (SELECT owner, table_name, constraint_name
   FROM RESTORE_TMP_CONSTRAINTS
   WHERE  status = 'ENABLED'
          And owner = 'MUIPROMO'
   ORDER BY constraint_type DESC)
  LOOP
    dbms_utility.exec_ddl_statement('alter table "' || c.owner || '"."' || c.table_name || '" disable constraint ' || c.constraint_name);
  END LOOP;

  

  /* ACL */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'ACL') THEN 
    DELETE FROM ACL ; 
    
    INSERT INTO ACL (    ID,    ROLE_ID,    UI_ID,    COMPONENT_CLASS,    COMPONENT,    VISIBLE,    EDITABLE,    ENABLED  )
    SELECT               ID,    ROLE_ID,    UI_ID,    COMPONENT_CLASS,    COMPONENT,    VISIBLE,    EDITABLE,    ENABLED   
    FROM ACL_BACKUP
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ; 
    
    insert into RESTORE_LOG (  SAFE_BACKUP_ID , BACKUP_ID_RESTORED , RESTORED_DATE , TABLE_NAME  ) 
    values                  (  v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , 'ACL' ) ;
  END IF ;  
  
  /* CONFIGURATION */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'CONFIGURATION') THEN 
    DELETE FROM CONFIGURATION ;
    
    INSERT INTO CONFIGURATION (   ID_CONFIGURATION,    PATH,    JSON,    TYPE )
    SELECT                        ID_CONFIGURATION,    PATH,    JSON,    TYPE 
    FROM CONFIGURATION_BACKUP 
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ;  
            
    insert into RESTORE_LOG (  SAFE_BACKUP_ID , BACKUP_ID_RESTORED , RESTORED_DATE , TABLE_NAME  ) 
    values                  (  v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , 'CONFIGURATION' ) ;
  END IF ; 
  
   /* MENU */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'MENU') THEN 
    DELETE FROM MENU ; 
    
    INSERT INTO MENU  (    ID_MENU,    PARENT_ID,    LABEL,    URL,    BEAN,    ORDER_ID,    EXTERNAL_LINK,    TEMPLATE  )
    SELECT                 ID_MENU,    PARENT_ID,    LABEL,    URL,    BEAN,    ORDER_ID,    EXTERNAL_LINK,    TEMPLATE
    FROM MENU_BACKUP 
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ;  
            
    insert into RESTORE_LOG (  SAFE_BACKUP_ID , BACKUP_ID_RESTORED , RESTORED_DATE , TABLE_NAME  ) 
    values                  (  v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , 'MENU' ) ;
  END IF ; 
  
  
   /* MENU_ROLES */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'MENU_ROLES') THEN 
    DELETE FROM MENU_ROLES ; 
    
    INSERT INTO MENU_ROLES      (  ID_MENU,  ID_ROLES  )
    SELECT                         ID_MENU,    ID_ROLES 
    FROM MENU_ROLES_BACKUP 
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ;  
            
    insert into RESTORE_LOG (  SAFE_BACKUP_ID , BACKUP_ID_RESTORED , RESTORED_DATE , TABLE_NAME  ) 
    values                  (  v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , 'MENU_ROLES' ) ;
  END IF ; 
  
  /* ROLES */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'ROLES') THEN 
    DELETE FROM ROLES ; 
    
    INSERT INTO ROLES          (   ID,    NAME,    DESCRIPTION,    HELP_FILENAME,    HELP_DATA  )
    SELECT                         ID,    NAME,    DESCRIPTION,    HELP_FILENAME,    HELP_DATA 
    FROM ROLES_BACKUP 
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ;  
            
    insert into RESTORE_LOG (  SAFE_BACKUP_ID , BACKUP_ID_RESTORED , RESTORED_DATE , TABLE_NAME  ) 
    values                  (  v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , 'ROLES' ) ;
  END IF ; 
  
  
  /* ROLES */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'ROLES') THEN 
    DELETE FROM ROLES ; 
    
    INSERT INTO ROLES          (   ID,    NAME,    DESCRIPTION,    HELP_FILENAME,    HELP_DATA  )
    SELECT                         ID,    NAME,    DESCRIPTION,    HELP_FILENAME,    HELP_DATA 
    FROM ROLES_BACKUP 
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ;  
            
    insert into RESTORE_LOG (  SAFE_BACKUP_ID , BACKUP_ID_RESTORED , RESTORED_DATE , TABLE_NAME  ) 
    values                  (  v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , 'ROLES' ) ;
  END IF ; 
  
  /* UI */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'UI') THEN 
  
    DELETE FROM UI ; 
    
    INSERT INTO UI             (    ID,    NAME,    DESCRIPTION  )
    SELECT                          ID,    NAME,    DESCRIPTION 
    FROM UI_BACKUP 
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ; 
            
    insert into RESTORE_LOG (  SAFE_BACKUP_ID , BACKUP_ID_RESTORED , RESTORED_DATE , TABLE_NAME  ) 
    values                  (  v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , 'UI' ) ;
  END IF ; 
  
   /* USERS */ 
  IF (v_TABLE_NAME = '*' OR v_TABLE_NAME = 'USERS') THEN 
    DELETE FROM USERS ; 
    
    INSERT INTO USERS        (  ID,  NAME,  FILTERS,  ROLE_ID,  HIDDEN_COLS,  CAM_LOGIN,  NAMESPACE_LOGIN,  ADMIN_FILTERS,  DESCRIPTION,  OLD_ROLE_ID) 
    SELECT                      ID,  NAME,  FILTERS,  ROLE_ID,  HIDDEN_COLS,  CAM_LOGIN,  NAMESPACE_LOGIN,  ADMIN_FILTERS,  DESCRIPTION,  OLD_ROLE_ID
    FROM USERS_BACKUP 
    WHERE  BACKUP_ID  = v_BACKUP_ID_RESTORED ; 
    
  END IF ; 
  
  /* ALL DONE */  
  insert into RESTORE_LOG (  SAFE_BACKUP_ID ,   BACKUP_ID_RESTORED ,     RESTORED_DATE , TABLE_NAME  ) 
  values                  (v_SAFE_BACKUP_ID , v_BACKUP_ID_RESTORED  ,  v_RESTORED_DATE , '*'         ) ;
  
  

  
  /* RIABILITO I CONSTRAINT NELLA TABELLE , PRIMA LE PRIMARY KEY  */ 
  FOR c IN
  (SELECT owner, table_name, constraint_name  
   FROM RESTORE_TMP_CONSTRAINTS
   WHERE status = 'ENABLED'
        AND owner = 'MUIPROMO'
        AND  constraint_type = 'P' 
   ORDER BY constraint_type)
  LOOP
    dbms_utility.exec_ddl_statement('alter table "' || c.owner || '"."' || c.table_name || '" enable constraint ' || c.constraint_name);
  END LOOP;

  /* RIABILITO I CONSTRAINT NELLA TABELLE , POI GLI ALTRI  */ 
  FOR c IN
  (SELECT owner, table_name, constraint_name  
   FROM RESTORE_TMP_CONSTRAINTS
   WHERE status = 'ENABLED'
        AND owner = 'MUIPROMO'
        AND  constraint_type <> 'P' 
   ORDER BY constraint_type)
  LOOP
    dbms_utility.exec_ddl_statement('alter table "' || c.owner || '"."' || c.table_name || '" enable constraint ' || c.constraint_name);
  END LOOP;
  
  
  COMMIT ; 
  
  
END IF ;



EXCEPTION  -- exception handlers begin
   WHEN OTHERS THEN  -- handles all other errors
      ROLLBACK;


END P_CONFIG_RESTORE;