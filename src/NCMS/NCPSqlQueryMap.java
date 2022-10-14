/**
	@SWING������Ʈ NCPSqlQueryMap.java 
	*N_Player Sql class
*/

package NCMS;

public abstract class NCPSqlQueryMap
{
	/**
	*	@method	getPInsertQuery()
	*   @G_MEMBER insert Query
	*/
	public static String getPInsertQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" INSERT INTO                               \n"); 
		sb.append(" N_PLAYER               ( N_CODE           \n"); 
        sb.append("                         ,N_NAME           \n");
        sb.append("                         ,N_POSITION       \n");
        sb.append("                         ,N_BIRTH          \n");
        sb.append("                         ,N_JOIN           \n");
        sb.append("                         ,N_PHOTO )        \n");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?)                 \n");

		return sb.toString();
	}	//	end of getPInsertQuery()

	public static String getPUpdateQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" UPDATE N_PLAYER                  \n");          
        sb.append(" SET    N_NAME     = ?            \n");
        sb.append("       ,N_POSITION = ?            \n");
        sb.append("       ,N_BIRTH    = ?            \n");
        sb.append("       ,N_JOIN     = ?            \n");
        sb.append("       ,N_PHOTO    = ?            \n");
        sb.append("       ,N_UPDATE   = SYSDATE      \n"); 
        sb.append(" WHERE  N_CODE     = ?            \n");
		
		return sb.toString();
	}	//	end of getPUpdateQuery()

	public static String getPDeleteQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" UPDATE N_PLAYER                  \n");          
        sb.append("       ,N_ETC    = 'N'          \n");
        sb.append("       ,N_UPDATE   = SYSDATE      \n"); 
        sb.append(" WHERE  N_CODE     = ?            \n");
		
		return sb.toString();
	}	//	end of getPDeleteQuery()

	public static String getPSelectQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT N_CODE            \n");
        sb.append("       ,N_NAME            \n");
        sb.append("       ,N_POSITION        \n");
        sb.append("       ,N_BIRTH           \n");
        sb.append("       ,N_JOIN            \n");
        sb.append("       ,N_PHOTO           \n");
        sb.append(" FROM   N_PLAYER          \n");
        sb.append(" WHERE  N_NAME = ?        \n");
        sb.append(" AND    N_ETC  = 'Y'      \n");
		
		return sb.toString();
	}	//	end of getPSelectQuery()

	public static String getPALLSelectQuery(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT N_CODE            \n");
        sb.append("       ,N_NAME            \n");
        sb.append("       ,N_POSITION        \n");
        sb.append("       ,N_BIRTH           \n");
        sb.append("       ,N_JOIN            \n");
        sb.append("       ,N_PHOTO           \n");
        sb.append(" FROM   N_PLAYER          \n");
		
		return sb.toString();
	}	//	end of getPALLSelectQuery()


}	//	end of NCPSqlQueryMap class