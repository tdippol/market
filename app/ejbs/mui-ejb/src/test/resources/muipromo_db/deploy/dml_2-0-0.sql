create table temporary(id integer);

insert into menu 
    (id_menu, parent_id, label, url, bean, order_id, external_link, template) 
values 
    (MENU_SEQ.nextval, null, 'Custome Engagement', null, null, 98, 0, 1);
    
insert into temporary(id) select max(id_menu) from menu;

insert into menu 
    (id_menu, parent_id, label, url, bean, order_id, external_link, template) 
values 
    (MENU_SEQ.nextval, (select max(id) from temporary), 'Visualizza Promo','dbpromo/visualizzaPromo.xhtml','visualizzaPromo',0,0,0);
    
insert into menu 
    (id_menu, parent_id, label, url, bean, order_id, external_link, template) 
values 
    (MENU_SEQ.nextval, (select max(id) from temporary), 'Crea Promozione','dbpromo/creaPromo.xhtml','creaPromo',1,0,0);
    
insert into menu 
    (id_menu, parent_id, label, url, bean, order_id, external_link, template) 
values 
    (MENU_SEQ.nextval, (select max(id) from temporary), 'Modifica Promozione','dbpromo/editPromo.xhtml','editPromo',2,0,0);

insert into menu 
    (id_menu, parent_id, label, url, bean, order_id, external_link, template) 
values 
    (MENU_SEQ.nextval, (select max(id) from temporary), 'Negozi Promo','dbpromo/promoShops.xhtml','promoShops',3,0,0);

insert into menu 
    (id_menu, parent_id, label, url, bean, order_id, external_link, template) 
values 
    (MENU_SEQ.nextval, (select max(id) from temporary), 'Scheda Promo','dbpromo/schedaPromo/schedaPromo.xhtml','schedaPromo',4,0,0);
	
insert into menu 
    (id_menu, parent_id, label, url, bean, order_id, external_link, template) 
values 
    (MENU_SEQ.nextval, (select max(id) from temporary), 'Pianificazione','dbpromo/pianificazione/pianificazionePromo.xhtml','pianificazionePromo',60,0,1);
    
drop table temporary;

