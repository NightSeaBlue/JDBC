/**
	@SWING������Ʈ NCMSqlQueryMap.java 
	*M_Member Sql class
*/
package NCMS;

public abstract class NCMSqlQueryMap
{
	/**
	*	@method	getmInsertQuery()
	*   @G_MEMBER insert Query
	*/
	public static String getMInsertQuery(){
		
		StringBuffer sb = new StringBuffer();

		sb.append(" INSERT INTO                               \n"); 
		sb.append(" G_MEMBER               ( G_CODE           \n"); 
        sb.append("                         ,G_IDENTITY       \n");
        sb.append("                         ,G_PASSWD         \n");
        sb.append("                         ,G_NAME           \n");
        sb.append("                         ,G_GENDER         \n");
        sb.append("                         ,G_BIRTH          \n");
        sb.append("                         ,G_EMAIL          \n");
        sb.append("                         ,G_ADDR           \n");
        sb.append("                         ,G_TEL)           \n");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  \n");

		return sb.toString();
	}	//	end of getmInsertQuery()
	

	public static String getMUpdateQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" UPDATE G_MEMBER                  \n");          
        sb.append(" SET    G_PASSWD   = ?            \n"); 
        sb.append("       ,G_NAME     = ?            \n");
        sb.append("       ,G_GENDER   = ?            \n");
        sb.append("       ,G_BIRTH    = ?            \n");
        sb.append("       ,G_EMAIL    = ?            \n");  
        sb.append("       ,G_ADDR     = ?            \n");
        sb.append("       ,G_TEL      = ?            \n");
        sb.append("       ,G_UPDATE   = SYSDATE      \n"); 
        sb.append(" WHERE  G_IDENTITY = ?            \n");
		
		return sb.toString();
	}	//	end of getmUpdateQuery()

	public static String getMDeleteQuery(){
		
		StringBuffer sb = new StringBuffer();

		sb.append(" UPDATE                         \n");
		sb.append("        G_MEMBER                \n");
        sb.append(" SET                            \n");
		sb.append("        G_ETC      = ?          \n");
		sb.append("       ,G_UPDATE   = SYSDATE    \n");
		sb.append(" WHERE                          \n"); 
		sb.append("        G_IDENTITY = ?          \n");

		return sb.toString();
	}	//	end of getMDeleteQuery()

	public static String getMSelectQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT G_CODE            \n");
        sb.append("       ,G_IDENTITY        \n");
        sb.append("       ,G_PASSWD          \n");
        sb.append("       ,G_NAME            \n");
        sb.append("       ,G_GENDER          \n");
        sb.append("       ,G_BIRTH           \n");
        sb.append("       ,G_EMAIL           \n");
        sb.append("       ,G_ADDR            \n");
        sb.append("       ,G_TEL             \n");
        sb.append(" FROM   G_MEMBER          \n");
        sb.append(" WHERE  G_IDENTITY = ?    \n");
        sb.append(" AND    G_ETC = 'Y'       \n");
		
		return sb.toString();
	}	//	end of getMSelectQuery()

	public static String getMUpdateQueryUser(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" UPDATE G_MEMBER                  \n");          
        sb.append("       ,G_PASSWD   = ?            \n"); 
        sb.append("       ,G_NAME     = ?            \n");
        sb.append("       ,G_EMAIL    = ?            \n");  
        sb.append("       ,G_ADDR     = ?            \n");
        sb.append("       ,G_TEL      = ?            \n");
        sb.append("       ,G_UPDATE   = SYSDATE      \n"); 
        sb.append(" WHERE  G_IDENTITY = ?            \n");

		return sb.toString();
	}	//	end of getmUpdateQuery()

	public static String getMAllSelectQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT G_CODE            \n");
        sb.append("       ,G_IDENTITY        \n");
        sb.append("       ,G_PASSWD          \n");
        sb.append("       ,G_NAME            \n");
        sb.append("       ,G_GENDER          \n");
        sb.append("       ,G_BIRTH           \n");
        sb.append("       ,G_EMAIL           \n");
        sb.append("       ,G_ADDR            \n");
        sb.append("       ,G_TEL             \n");
		sb.append("       ,G_INDATE          \n");
		sb.append("       ,G_UPDATE          \n");
        sb.append(" FROM   G_MEMBER          \n");
        sb.append(" AND    G_ETC = 'Y'       \n");

		return sb.toString();
	}	//	end of getMAllSelectQuery()

	public static String getMLoginQuery(){
		
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT                       \n");
        sb.append("        G_IDENTITY            \n");
        sb.append("       ,G_PASSWD              \n");
		sb.append("       ,G_NAME                \n");
        sb.append(" FROM                         \n");
        sb.append("        G_MEMBER              \n");
        sb.append(" WHERE  G_IDENTITY = ?        \n"); 
        sb.append(" AND    G_PASSWD   = ?        \n");
        sb.append(" AND    G_ETC      = 'Y'      \n");
	
		return sb.toString();
	}	//	end of getLoginQuery

	public static String getMIDCheckQuery(){	//	�ߺ�Ȯ��
		
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT                       \n");
        sb.append("        G_IDENTITY            \n");
        sb.append("       ,G_ETC                 \n");
        sb.append(" FROM                         \n");
        sb.append("        G_MEMBER              \n");
        sb.append(" WHERE  G_IDENTITY = ?        \n");
		sb.append(" AND    G_ETC      = 'Y'      \n");
	
		return sb.toString();
	}	//	end of getMIDCheckQuery()

	public static String getMPWFindQuery(){	//	�н����� ã��
		
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT                       \n");
        sb.append("        G_PASSWD              \n");
        sb.append("       ,G_ETC                 \n");
        sb.append(" FROM                         \n");
        sb.append("        G_MEMBER              \n");
        sb.append(" WHERE  G_IDENTITY = ?        \n");
		sb.append(" AND    G_NAME     = ?        \n");
		sb.append(" AND    G_EMAIL    = ?        \n");
        sb.append(" AND    G_ETC      = 'Y'      \n");
	
		return sb.toString();
	}	//	end of getMPWFindQuery()

	public static String getMIDFindQuery(){	//	���̵� ã��
		
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT                       \n");
        sb.append("        G_IDENTITY            \n");
        sb.append("       ,G_ETC                 \n");
        sb.append(" FROM                         \n");
        sb.append("        G_MEMBER              \n");
		sb.append(" WHERE  G_NAME     = ?        \n");
		sb.append(" AND    G_EMAIL    = ?        \n");
        sb.append(" AND    G_ETC      = 'Y'      \n");
	
		return sb.toString();
	}	//	end of getMPWCheckQuery()

}	//	end of NCMSqlQueryMap class