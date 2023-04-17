package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.dboperator;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.dboperator.common.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.model.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.database.dboperator.mysql.*;
import java.util.*;

public class StCommandManager extends StSupporterBase
{
    private static final Map<String, IDbCommand> a;

    public static IDbCommand getDbCommand(final String s) {
        if (!DatabaseType.isAvailableType(s)) {
            return null;
        }
        IDbCommand dbCommand;
        if ((dbCommand = StCommandManager.a.get(s)) == null) {
            final String lowerCase = s.toLowerCase();
            switch (lowerCase) {
                case "mysql": {
                    dbCommand = new MysqlDbCommand();
                    break;
                }
                default: {
                    dbCommand = null;
                    break;
                }
            }
            if (dbCommand != null) {
                StCommandManager.a.put(s, dbCommand);
            }
        }
        return dbCommand;
    }

    static {
        a = new HashMap<String, IDbCommand>();
    }
}
