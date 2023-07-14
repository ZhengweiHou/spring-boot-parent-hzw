package cn.sccfc.zacps.ops.jdbc.datasource;


import lombok.Getter;

@Getter
public enum DatabaseType {
    OPS("OPS", "信贷云opsDB"),
    TRANS("TRANS", "信贷云前置DB"),
    USER("USER", "信贷云额度DB"),
    SCUSER("SCUSER", "会员信息DB"),
	SCQUNARFRONT("SCQUNARFRONT","渠道DB"),
	AFA("AFA","前置DB");
    private String dbKey;
    private String dbDesc;

    DatabaseType(String dbKey, String dbDesc) {
        this.dbKey = dbKey;
        this.dbDesc = dbDesc;
    }
}
