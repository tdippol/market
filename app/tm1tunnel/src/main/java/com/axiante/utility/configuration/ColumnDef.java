package com.axiante.utility.configuration;

import com.axiante.mui.common.utility.CellNameMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColumnDef implements Serializable{
    // campi che servono nella generazione del json
    @JsonInclude(Include.NON_NULL)
    String headerName;
    @JsonInclude(Include.NON_NULL)
    String field;
    @JsonInclude(Include.NON_NULL)
    Integer width;
    @JsonInclude(Include.NON_NULL)
    Boolean editable;
    @JsonInclude(Include.NON_NULL)
    String columnGroupShow;
    @JsonInclude(Include.NON_NULL)
    String aggFunc;
    @JsonInclude(Include.NON_NULL)
    String aggFuncParam;
    @JsonInclude(Include.NON_NULL)
    String aggFuncParam2;
    @JsonInclude(Include.NON_NULL)
    String aggFuncParam3;
    @JsonInclude(Include.NON_NULL)
    String [] type;
    @JsonInclude(Include.NON_NULL)
    Boolean hide;
    @JsonInclude(Include.NON_NULL)
    Boolean rowGroup;
    @JsonInclude(Include.NON_NULL)
    Boolean marryChildren;
    @JsonInclude(Include.NON_NULL)
    String headerClass;
    @JsonInclude(Include.NON_NULL)
    Boolean openByDefault;
    @JsonInclude(Include.NON_NULL)
    String cellClass;
    @JsonInclude(Include.NON_NULL)
    Map<String ,String  >cellClassRules;

    // campi che servono per la creazione dell'albero
    @Setter(value = AccessLevel.NONE)
    @JsonIgnore
    private boolean root = false;
    @JsonIgnore
    LinkedHashMap<String, ColumnDef> children;
    @JsonIgnore
    String[] customHeaders;
    @JsonIgnore
    public String[] getGenealogy() {
        if (this.getField() != null ) {
            return this.getField().split(Pattern.quote("$$"));
        }
        return null;
    }

    public ColumnDef clone(boolean header) {
        ColumnDef def = new ColumnDef();
        if ( !header ) {
            def.headerName        = this.getHeaderName()      ;
            def.field             = this.getField()           ;
            def.width             = this.getWidth()           ;
            def.editable          = this.getEditable()        ;
            def.columnGroupShow   = this.getColumnGroupShow() ;
            def.aggFunc           = this.getAggFunc()         ;
            def.aggFuncParam      = this.getAggFuncParam()    ;
            def.aggFuncParam2     = this.getAggFuncParam2()   ;
            def.aggFuncParam3     = this.getAggFuncParam3()   ;
            def.type              = this.getType()            ;
            def.hide			  = this.getHide()		      ;
            def.rowGroup 		  = this.getRowGroup()        ;
            def.cellClass 		  = this.getCellClass()       ;
            if ( this.getCellClassRules() != null ) {
            	def.cellClassRules = new HashMap<String, String>();
            	for ( Map.Entry<String,String> entry : this.getCellClassRules().entrySet() ) {
            		def.getCellClassRules().put(entry.getKey(), entry.getValue());
            	}
            }
        }
        if ( header ) {
            def.headerClass       = this.getHeaderClass()     ;
            def.openByDefault     = this.getOpenByDefault()   ;
        }

        return def;

    }
    @Override
    public ColumnDef clone() {
        return this.clone(false);
    }
    public static ColumnDef createRootNode() {
        ColumnDef rootNode = new ColumnDef();
        rootNode.root = true;
        return rootNode;
    }

    public ColumnDef insert(ColumnDef def) {
        if (!this.isRoot()) {
            return null;
        }
        ColumnDef insertionPoint =  ( this.getInsertionNode(def));
        if ( insertionPoint.getChildren() == null ) {
            insertionPoint.setChildren(new LinkedHashMap<String, ColumnDef>());
        }
        insertionPoint.getChildren().put(def.getGenealogy()[def.getGenealogy().length-1], def);
        return def;
    }

    protected ColumnDef getInsertionNode(final ColumnDef def) {
        if ( this.isRoot()) {
            if ( def.getGenealogy().length == 1) {
                // def e' figlio unico
                return this;
            } else {
                return this.getInsertionNode(this, def, def.getGenealogy().length);
            }
        }
        else {
            return null;// se non sono root non posso adottare un figlio unico
        }
    }

    private ColumnDef getInsertionNode(final ColumnDef start, final ColumnDef def, int level) {
        String[] genealogia = def.getGenealogy();
        String key = genealogia[genealogia.length - level ];
        if ( level == 1 ) {
            // cerco insertion point della foglia che sono io
            return start;
        }else {
            // sei gia' tra i miei 'sibilings?
            if ( start.getChildren() == null) {
                // no, creo una famiglia
                start.setChildren(new LinkedHashMap<String, ColumnDef>());
            }
            ColumnDef n = start.getChildren().get(key);
            if (n == null ) {
                n = new ColumnDef();
                n.setCustomHeaders(def.getCustomHeaders());
                n.field = key;
                start.getChildren().put(key, n);
            }
            return this.getInsertionNode(n,def, level-1);
        }
    }

    public void merge(ColumnDef over, boolean header) {
        if ( !header ) {
            this.headerName        = (over.getHeaderName()      != null ? over.getHeaderName()       : this.headerName      );
            this.field             = (over.getField()           != null ? over.getField()            : this.field           );
            this.width             = (over.getWidth()           != null ? over.getWidth()            : this.width           );
            this.editable          = (over.getEditable()        != null ? over.getEditable()         : this.editable        );
            this.columnGroupShow   = (over.getColumnGroupShow() != null ? over.getColumnGroupShow()  : this.columnGroupShow );
            this.aggFunc           = (over.getAggFunc()         != null ? over.getAggFunc()          : this.aggFunc         );
            this.aggFuncParam      = (over.getAggFuncParam()    != null ? over.getAggFuncParam()     : this.aggFuncParam    );
            this.aggFuncParam2     = (over.getAggFuncParam2()   != null ? over.getAggFuncParam2()    : this.aggFuncParam2   );
            this.aggFuncParam3     = (over.getAggFuncParam3()   != null ? over.getAggFuncParam3()    : this.aggFuncParam3   );
            this.hide			  = (over.getHide()			   != null ? over.getHide()			    : this.hide			  );
            this.rowGroup 		  = (over.getRowGroup()        != null ? over.getRowGroup()         : this.rowGroup        );
            this.type 		      = (over.getType()            != null ? over.getType()             : this.type            );
            this.cellClass 		  = (over.getCellClass()       != null ? over.getCellClass()        : this.cellClass       );
            this.cellClassRules = (over.getCellClassRules() != null && over.getCellClassRules().size() > 0 )? over.getCellClassRules() : this.cellClassRules;

        }
        if ( header ) {
            this.headerClass       = (over.headerClass     != null ? over.headerClass      : this.headerClass     );
            this.openByDefault     = (over.openByDefault   != null ? over.openByDefault    : this.openByDefault   );
        }
    }
    public void merge(ColumnDef over) {
        this.merge(over, false);
    }

    public ColumnDef fixAgGridNames() {
        this.field = CellNameMapper.map2agGrid(this.field);
        return this;
    }
}
