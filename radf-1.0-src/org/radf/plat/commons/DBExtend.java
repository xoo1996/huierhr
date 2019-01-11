package org.radf.plat.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.global.GlobalErrorMsg;

/**
 * ���ݿ���������࣬�ṩ���ֲ�����
 * <p>(1)ʹ��DBBase�����й���,�Ծ�̬��ʽִ�����ݲ���</p>
 * <p>(2)����ʵ��,�����ڲ���������</p>
 * <p>(3)ʹ���ڲ����ݿ�����ִ��getSequence��querySQL��ExecSQL������������ݿ����</p>
 * <p>(4)���ڲ����ݿ����ӣ�ͨ��beginTrans��commitTrans��rollbackTrans����������</p>
 *    
 * @author zqb
 * @version 1.1
 */
public class DBExtend extends DBUtil{
    private  Connection con = null;
    private  PreparedStatement ps = null;
    private  ResultSet rs = null;
    
    public DBExtend() throws SQLException{
        try{
            openConnection();
        }catch(SQLException e){
            throw new SQLException("[DBExtend]����ʧ�ܣ�"+GlobalErrorMsg.DB_CONNECTION_ERROR+e.getMessage());
        }
    }
    
    /**
     * �ͷ��ڲ����ݿ�������Դ
     * @param con
     * @param rs
     * @param statement
     * @throws SQLException
     */
    public void destroy(){
        closeResult();
        closeStatement();
        closeConnection();
    }
    
    /**
     * ʹ���ڲ��Զ����������ݿ����ӣ���ȡָ�����Ƶ�����
     * @param sequenceName  ��������
     * @return java.lang.String
     * @exception SQLException
     */
    public String getSequence(String sequenceName) throws SQLException {
        return getSequence(con,sequenceName);
    }
    
    /**
     * ��ȡ���ݿ�ϵͳ��ǰʱ�䣬��Բ�ͬ�����ݿ⣬SQL������ⲿ����
     * @return
     * @throws SQLException
     */
    public String getSysDate()throws SQLException {
        return getSysDate(con);
    }
    /**
     * ��ȡ���ݿ�ϵͳ��ǰʱ��
     * @return java.sql.Date
     * @throws SQLException
     */
    public java.sql.Date getSysDate2() throws SQLException {
        return getSysDate2(con);
    }

    /**
     *ʹ���ڲ����ݿ����ӣ���ѯ���ݲ����ؽ������
     *@param String sql��Ҫ��ѯ��SQL���
     *@return ResultSet rs����ѯ�Ľ����
     * @throws SQLException
     **/
    public ResultSet query(String sql) throws SQLException{
        if(sql==null)
            throw new SQLException("Ҫ��ѯ��SQL���Ϊ��");
        try{
            closeResStat();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch(SQLException se){
            System.out.println("����query����(" + String.valueOf(se.getErrorCode()) + ")��" + se.getMessage());
        }
        return rs;
    }
    /**
     * ʹ���ڲ����ݿ����ӣ����ݴ����SQLִ�в�ѯ��������һ��Collection�ļ�¼������
     * ����ÿ����¼��HashMap��ʽ��ţ�HashMap��key���Ǹ�ֵ��Ӧ�ֶε�˳��ţ���1��ʼ��
     * ע�⣺���������ʺ϶�ȡclob��blob�ȴ���������
     * @param sql
     * @return
     * @throws SQLException
     */
    public Collection querySQL(String sql) throws SQLException{
        try{
            query(sql);
            return ResultToCollection(rs,"1");
        }finally{
            closeResStat();
        }
    }
    /**
     * ʹ���ڲ����ݿ����ӣ�ִ���޲����������
     * �����Զ��ύ��Ҳ�����ͷ��������ݿ�Connection��Դ��
     * @param sql  Ҫִ�е����ݲ�����䡣
     * @throws SQLException   ִ�����ݲ������ʧ��ʱ���׳��쳣��
     */
    public void execSQL(String sql) throws SQLException {
        //�����ݿ����Ӳ����ԭ����¼��
        execSQL(con,sql);
    }


    /**
     * ִ�ж���SQL�����²������ڲ������������
     * @param sqlList(List)
     * @return void
     * @exception SQLException
     */
    public void execSQL(List sqlList) throws SQLException {
        if (sqlList == null || sqlList.size() == 0)
            return;
        try {
            closeResStat();
            beginTrans();
            Statement stmt = null;
            try{
                stmt = con.createStatement();
                for (int i = 0; i < sqlList.size(); i++) {
                    stmt.executeUpdate((String) sqlList.get(i));
                }
            }finally{
                stmt.close();
            }
            commitTrans();
        } catch (SQLException ex) {
            rollbackTrans();
            throw new SQLException("���ݸ���ʧ��,������" + ex.getCause() + ",������Ϣ" + ex.getMessage());
        }finally{
        }
    }
    /**
     * �������������ݿ�����ִ�д洢���̲��ύ����(����׼ȷ��δ��֤)
     * @param ProName     ������
     * @param ParamValue  ����ֵ����
     * @param ParamType   ������������
     * @param ParamNum    �洢������Ҫ�Ĳ�������
     * @return �����ַ�����ǰ��λ��ʾ���ش��룬2λ���Ǵ洢���̷��ص��ı���
     * @throws SQLException  ִ�д洢���̳����׳��쳣��
     */
    public String exproAndCommit(String ProName, String ParamValue[],
            String ParamType[], int ParamNum) throws SQLException {
        return exproAndCommit(con,ProName,ParamValue,ParamType,ParamNum);
    }
    /**
     * ����sql����ȡ�������������ݵ�sql��������"select count(*)..."��ʽ
     * @param sql
     * @return
     * @throws SQLException
     */
    public int getRowCount(String sql) throws SQLException{
        return getRowCount(con,sql);
    }
    
    /**
     * �ж��ڲ�ResultSet���ݼ�����һ����¼�Ƿ����
     * @return boolean ��һ����¼�Ƿ����
     * @exception SQLException
     */
    public boolean nextRow() throws SQLException {
        if (rs == null)
            throw new SQLException("ResultSet is null");
        return rs.next();
    }
    /**
     * ��ȡ�ڲ�ResultSet���ݼ���ǰ�����к�
     * @return
     * @throws SQLException
     */
    public int getRow() throws SQLException {
        if (rs == null)
            throw new SQLException("ResultSet is null");
        return rs.getRow();
    }


    /**
     * ��ʼ����
     * @throws SQLException  ����ʼʧ��ʱ���׳��쳣��
     */
    public void beginTrans() throws SQLException {
        try {
            verifyConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new SQLException("���ݿ����񴴽�ʧ�ܣ�" + e.getMessage());
        }
    }

    /**
     * �ύ�����ͷ�ps��rs�������ݿ����Ӳ��ͷ�
     * @throws SQLException �ύʧ��ʱ���׳��쳣��
     */
    public void commitTrans() throws SQLException {
        try {
            verifyConnection();
            con.commit();
            con.setAutoCommit(true);
            closeResStat();
        } catch (SQLException e) {
            throw new SQLException("���ݿ��ύ����: " + e.getMessage());
        }
    }

    /**
     * �ع������ͷ�ps��rs�������ݿ����Ӳ��ͷ�
     * @throws SQLException �ع�ʧ��ʱ���׳��쳣��
     */
    public void rollbackTrans() throws SQLException {
        try {
            verifyConnection();
            con.rollback();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            throw new SQLException("���ݿ�ع�����: " + e.getMessage());
        } finally {
            closeResStat();
        }
    }

    
    /**
     * У�����ݿ������Ƿ���ڣ�������������׳��쳣
     * @throws SQLException
     */
    private void verifyConnection()throws SQLException {
        if(con==null||con.isClosed()){
            throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR);
        }
    }
    
    /**
     * �ر�ResultSet
     */
    private void closeResult(){
        closeResult(rs);
    }
    /**
     * �ر�Statement
     */
    private void closeStatement(){
        closeStatement(ps);
    }
    /**
     * �ر����ݿ�����
     */
    private void closeConnection(){
        closeConnection(con);
    }
    /**
     * �ͷ�ResultSet��Statement
     * @throws SQLException
     */
    private void closeResStat(){
        closeResult();
        closeStatement();
    }
    
    /**
     * ��������SQL����PreparedStatement �Ե��������õ����ݽ���װ�ɲ�����ʽ���ݲ�ִ��
     * ���磺select * from A where B = 'a'
     *     ���Ϊselect * from A where B = ? ,�����ݲ���"a"
     * @param Connect con ���ݿ�����
     * @param String queryy ���ݿ����
     * @return PreparedStatement
     */
    private PreparedStatement createPreparedStatement(String querry) throws SQLException {
        ArrayList targetStrings = new ArrayList();
        String processedQuerry = "";
        int startIndex = -1; // ��ʼʱ���˲���������ִ�зֽ�������ǲ�ִ�зֽ����
        if (startIndex != -1) {
            int index = startIndex;
            int literalStart = -1;
            while (index < querry.length()) {
                // �����ַ����д��ڵ�������ţ�'
                if (querry.charAt(index) == '\'') {
                    if (literalStart == -1 && index + 1 < querry.length()) {
                        literalStart = index + 1; // ����" ' "�������ַ����е�λ��
                    } else {
                        String targetString = querry.substring(literalStart,
                                index);// ������������֮�������
                        targetStrings.add(targetString);
                        literalStart = -1;
                        processedQuerry += "?";
                        index++;
                    }
                }
                if (index < querry.length() && literalStart == -1) {
                    processedQuerry += querry.charAt(index);
                }
                index++;
            }
            ps = con.prepareStatement(processedQuerry + " ");
            Iterator it = targetStrings.iterator();
            int counter = 1;
            while (it.hasNext()) {
                String arg = (String) it.next();
                ps.setString(counter++, arg);
            }
        } else {
            ps = con.prepareStatement(querry);
        }
        return ps;
    }
    /**
     * �����ڲ����ݿ�����
     * �������Ǳ����ڲ�Ĭ�����ӣ�Ҳ���Է��ص�����ʹ�� 
     * @return  Connection ����������
     * @throws SQLException
     */
    private Connection openConnection()throws SQLException {
        //��������
        try{
            if(con==null||con.isClosed()){
                destroy();
                con = getConnection();
            }else{
                closeResStat();
            }
        }catch(SQLException se){
            throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR + se.getMessage());
        }catch(NoConnectionException se){
            throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR + se.getMessage());
        }
        return con;
    }
    protected void finalize()throws Throwable{
        destroy();
    }
}
