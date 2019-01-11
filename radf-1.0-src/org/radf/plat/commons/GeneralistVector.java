package org.radf.plat.commons;

import java.io.Serializable;
import java.util.Vector;



/**
 * ������һ��������<br>
 * �����е������ǰ����û��洢��˳����д洢�ġ�( ע�����漰�����ֵģ����Ǵ�0��ʼ�ǣ���ģ�������ط�����������)
 * ����������ص㣺
 * <br>
 * <li>�������е����ݿɵ�һά����ʹ�ã�Ҳ�ɵ���ά����ʹ��
 * <li>����ȷ�������ҵ�������ָ���ļ���Ӧ�����ݣ���Ҳ���Կ����ҵ�������ָ����ֵ��Ӧ�ļ��������Ǽ���ֵ������һһ��Ӧ��ϵ��
 * <li>���ݵĴ洢�����û������˳����д洢
 * <li>ʹ�ü�ֵ��
 * <li>��ʵ�ָ��ӵĶ�ά������ݴ洢
 * @author wangbaowei
 */
public class GeneralistVector implements Serializable{
	private static final long serialVersionUID = 1L;
    private String ERROR_DATA_MSG = " ���ʵ��������!";
    private String ERROR_EI_MSG = " �д��ڴ������ʵ������";
    
    
//	public int length;

	/** ���� */
	private int columCount;

	/** ���� */
	private int rowCount;

	private Vector vector = new Vector(0, 1);

	private Vector keyVector = new Vector(0, 1);

	private Vector valueVector = new Vector(0, 1);

	private Object synObj = new Object();

	private EngrossVector rowKeys = new EngrossVector();

	// {
	// length=keyVector.size();
	// }

	/**
	 * �������в��ҵ�i������ΪXXX��ֵ
	 * 
	 * @param i
	 *            �к� ��0��ʼ
	 * @param id
	 *            �û����ֶα�ʶ(����ָ�������е��ֶα�ʶ)
	 * @return ���ص���key,�ɸ��ݸÿ��Ի�ȡ���������Ϣ
	 */
	/* <li> 0 -- ��ʾ�����ƣ��������������в��� */

	public Object getObjectAtRowAndByName(int i, String id)
	{
		if(id==null)
		{
			System.out.println("GeneralistVector.getObjectAtRowAndByName()" +ERROR_DATA_MSG);
			return null;
		}
		Vector datas = getObjectsAtRowOf(i);

		for (int j = 0; j < datas.size(); j++)
		{
			String s = (String) datas.get(j);
			if(s==null)
			{
				System.err.println("GeneralistVector.getObjectAtRowAndByName() �г������⣬���������ݹ�ʣ!");
				return null;
			}
			if(s.indexOf(id)<0)
				continue;
			int p = s.indexOf("\\");
			if (p > 0)
			{
				String findString = null;
				findString = s.substring(p + 1);
				if (id.trim().equals(findString))
				{
					return s;
				}
			} 
		}
		System.out.println("GeneralistVector.getObjectAtRowAndByName()" + ERROR_DATA_MSG);
		return null;
	}
	
	
	/**
	 * ��ȡ�����ݶ�Ӧ�ļ����ж�
	 * �÷��������Ķ����ǵ�i��
	 * @param i �к�
	 * @param ids Ҫ���ҵ��еı�ʶ�ļ���,�༴���ݿ���ȡ����������Ҫ��ֵ��������ļ���
	 * <br>��������ݿ���ȡ�������ݶ�Ӧ���ֶ���"B","C","F"���������ids�д洢�ľ���"B","C","F".
	 * @return ___________________________________________________
	 *        |____|_A__|__B__|__C__|__D__|__E__|__F__|__G__|__H__|
	 *        |_M__|_MA_|_MB__|_MC__|_MD__|_ME__|_MF__|_MG__|_MH__|
	 *        |_N__|_NA_|_NB__|_NC__|_ND__|_NE__|_NF__|_NG__|_NH__|
	 *        <br>
	 *        ����ֵΪ��(MB,B),(MC,C),(MF,F)�ļ���,��Ȼ�����i����0�ˡ�                
	 *          
	 */
	public GeneralistVector getKey_ColumSetAtRowAndByNames(int i, Vector ids)
	{
		GeneralistVector result= new GeneralistVector();
		if(ids==null)
		{
			System.out.println("GeneralistVector.getObjectAtRowAndByName()" +ERROR_DATA_MSG);
			return null;
		}
		//һ�����������ݶ�Ӧ�ļ�
		Vector datas = getObjectsAtRowOf(i);

		for (int j = 0; j < datas.size(); j++)
		{
			int k =0;
			while(ids.size()>0)
			{
				//���еļ���XXX\YYY����ʽ������YYY��λ�ò�����0��������ʹ��">"��
			 if(datas.get(j).toString().indexOf(ids.get(k).toString())>0)
			 {
				result.put(datas.get(j),ids.get(k));
				ids.remove(k);
				break;
			 }
			}
		}
//		for (int j = 0; j < datas.size(); j++)
//		{
//			String s = (String) datas.get(j);
//			for (int k = 0; k < ids.size(); k++)
//			{
//				
//				if(s.indexOf(ids.get(k).toString())<0)
//					continue;
//				else
//				{
//					int p = s.indexOf("\\");
//					if (p > 0)
//					{
//						String findString = null;
//						findString = s.substring(p + 1);
//						if (ids.get(k).toString().trim().equals(findString))
//						{
//							result.put(s,findString);
////							return s;
//						}
//					} 
//				}
//			}
//			
//		}
//		System.out.println("GeneralistVector.getObjectAtRowAndByName()" + Err.dataErr());
		return result;
	}

	// public Object getObjectAtRowAndByName(int i, String id, String scope)
	// {
	// if (scope == null)
	// {
	// System.out.println("GeneralistVector.getObjectAtRowAndByName()" + Err.ei());
	// return null;
	// }
	// Vector datas = getObjectsAtRowOf(i);
	//	
	// for (int j = 0; j < datas.size(); j++)
	// {
	// String s = (String) datas.get(j);
	// int p = s.indexOf("\\");
	// if (p > 0)
	// {
	// String findString = null;
	// if (scope.trim().equals("row"))
	// {
	// findString = s.substring(0, p);
	// } else
	// {
	// findString = s.substring(p + 1);
	// }
	// if (id.equals(findString))
	// {
	// return s;
	// }
	// } else
	// continue;
	// }
	// System.out.println("GeneralistVector.getObjectAtRowAndByName()"+Err.dataErr());
	// return null;
	// }

	/**
	 * ��ȡ��i�е����еļ�
	 */
	public Vector getObjectsAtRowOf(int i)
	{
		Vector v = new Vector(0, 1);
		for (int j = 0; j < columCount; j++)
		{
			v.add(this.getKeyAt(columCount * i + j));
		}
		return v;
	}

	/**
	 * ��ȡ������Ϊkey��Ҫ��ȡ������Ϣ����ͨ����key��ȡ��
	 * 
	 * @param row
	 * @param colum
	 * @return
	 */
	public Object getValueAt(int row, int colum)
	{
		return getValueAt(columCount * row + colum);
	}
	
	/**
	 * ��ȡ������Ϊkey��Ҫ��ȡ������Ϣ����ͨ����key��ȡ��
	 * 
	 * @param row
	 * @param colum
	 * @return
	 */
	public Object getKeyAt(int row, int colum)
	{
		return getKeyAt(columCount * row + colum);
	}

	/**
	 * �������
	 * 
	 * @param key
	 *            ��
	 * @param value
	 *            ֵ
	 */
	public void put(Object key, Object value)
	{
		boolean contain = keyVector.contains(key);
		int position = -1;
		if (contain)
		{
			position = remove(key);
			if (position != -1)
				synchronized (synObj)
				{
					if (key instanceof String)
					{
						String str = (String) key;
						// System.out.println("GeneralistVector.put() ��Ҫ���в���");
						if (str.indexOf("\\") >= 0)
							rowKeys.put(str.substring(0, str.indexOf("\\")));
					}
					keyVector.add(position, key);
					valueVector.add(position, value);
					vector.add(position, new Entity(key, value));
				}
		} else
			synchronized (synObj)
			{
				if (key instanceof String)
				{
					String str = (String) key;
					// System.out.println("GeneralistVector.put() ��Ҫ���в���");
					if (str.indexOf("\\") >= 0)
						rowKeys.put(str.substring(0, str.indexOf("\\")));
				}
				keyVector.add(key);
				valueVector.add(value);
				vector.add(new Entity(key, value));
			}
	}

	public EngrossVector getRowKeys()
	{
		return rowKeys;
	}

	/**
	 * ɾ����i������
	 * 
	 * @param i
	 *            ����
	 */
	public void remove(int i)
	{
		if (i < 0 || i >= getSize())
		{
			System.out.println("the data that you wants to remove is out of bound!");
			return;
		}
		synchronized (synObj)
		{
			keyVector.remove(i);
			valueVector.remove(i);
			vector.remove(i);
		}
	}

	/**
	 * ɾ����ֵΪkey������
	 * 
	 * @param key
	 *            ��ֵ
	 */
	public int remove(Object key)
	{
		int i = keyVector.indexOf(key);
		if (i < 0)
		{
			System.out.print("GeneralistVector.remove()---");
			System.out.println("the data you wants to remove does not exist!");
			return -1;
		}
		synchronized (synObj)
		{
			keyVector.remove(i);
			valueVector.remove(i);
			vector.remove(i);
		}
		return i;
	}

	/**
	 * ɾ����������
	 */
	public void removeAll()
	{
		keyVector.removeAllElements();
		valueVector.removeAllElements();
		vector.removeAllElements();
	}

	/**
	 * ��ȡ���������ݵĸ���
	 * 
	 * @return ���ݸ���
	 */
	public int getSize()
	{
		return keyVector.size();
	}

	/**
	 * ��ȡ��i�����ݵ�key
	 * 
	 * @param i
	 *            ����
	 * @return ��ֵ
	 */
	public Object getKeyAt(int i)
	{
		try
		{
			return ((Entity) vector.get(i)).getKey();
		} catch (RuntimeException e)
		{
			return null;
		}
	}

	/**
	 * getStringOfKey ��ȡ��i�����ݵ�key
	 * 
	 * @param i
	 *            ����
	 * @return ��ֵ
	 */
	public String getSOKeyAt(int i)
	{
		try
		{
			return (String) ((Entity) vector.get(i)).getKey();
		} catch (RuntimeException e)
		{
			return null;
		}
	}

	/**
	 * ��ȡ��ֵΪkey��ֵ��
	 * 
	 * @param key
	 * @return ��Ϊkey��ֵ
	 */
	public Object getValueOfKey(Object key)
	{
		int i = keyVector.indexOf(key);
		if (i < 0)
		{
			System.err.println("�� " + key + "  ������,���ʵ������ļ�ֵ��ȷ��!");
			return null;
		}
		return ((Entity) vector.get(i)).getValue();
	}
	/**
	 * ��ȡ��ֵΪkey��ֵ��
	 * 
	 * @param key
	 * @return ��Ϊkey��ֵΪ�ַ���
	 */
	public String getVOfKBySting(Object key)
	{
		return getValueOfKey(key).toString();
	}

	/**
	 * ��ȡֵΪvalue�ļ�,�÷�������һ��һ�����������ȷ�ģ����������ֵ������ͬ����
	 * 
	 * @param value
	 * @return ֵΪvalue�ļ�
	 */
	public Object getKeyOfValue(Object value)
	{
		int i = valueVector.indexOf(value);
		if (i < 0)
		{
			System.err.println("�� " + value + "  ������,���ʵ������ļ�ֵ��ȷ��!");
			return null;
		}
		return ((Entity) vector.get(i)).getKey();
	}

	/**
	 * ��ȡ��i�����ݵ�Value
	 * 
	 * @param i
	 *            ����
	 * @return ��ȡ��i�����ݵ�Value
	 */
	public Object getValueAt(int i)
	{
		try
		{
			return ((Entity) vector.get(i)).getValue();
		} catch (RuntimeException e)
		{
			return null;
		}
	}

	/**
	 * ��ȡ��i�����ݵ���Ϣ
	 * 
	 * @param i
	 * @return
	 */
	public String getInfoAt(int i)
	{
		Object key = getKeyAt(i);
		Object value = getValueAt(i);
		if (key != null)
		{
			return key.toString() + "  " + value;
		} else
			return null;
	}

	/**
	 * �ڲ�����
	 */
	private final class Entity implements Serializable
	{
		private static final long serialVersionUID = -9020882524547569325L;

		private Object key;

		private Object value;

		Entity(Object arg0, Object arg1)
		{
			key = arg0;
			value = arg1;
		}

		public Object getKey()
		{
			return key;
		}

		public void setKey(Object key)
		{
			this.key = key;
		}

		public Object getValue()
		{
			return value;
		}

		public void setValue(Object value)
		{
			this.value = value;
		}
	}

	public int getcolumCount()
	{
		return columCount;
	}

	/**
	 * �趨������ʵ�ְѸ������д洢�����ݿ���Ϊ��ά���ݡ�
	 * @param columCount
	 */
	public void setcolumCount(int columCount)
	{
		this.columCount = columCount;
	}
	
	/**
	 * �趨����������
	 * <br>��һά����ת���ɶ�ά����
	 * @param r ����
	 * @param c ����
	 */
	public void setRowCountAndColumCount(int r,int c)
	{
		this.rowCount=r;
		this.columCount=c;
	}

	/**
	 * ����
	 * @return
	 */
	public int getRowCount()
	{
		int c = getSize()/rowCount;
		return getSize()%rowCount==0?c:(c+1);
	}

//	public void setRowCount(int rowCount)
//	{
//		this.rowCount = rowCount;
//	}
    private class EngrossVector extends Vector implements Serializable{
        private static final long serialVersionUID = 1L;
        
        public EngrossVector(){
            super(0,1);
        }
        /**
         * ��������
         * @param o
         */
        public void put(Object o){
            if(contains(o))
                return ;
            add(o);
        }
    }
    public static void main(String[] args)
    {
        GeneralistVector v = new GeneralistVector();
        v.put("bb", "2");
        v.put("bb�� ", "1");
        v.put("bb", "3");
        System.out.println("v�Ĵ�С " + v.getSize());
        for (int i = 0; i < v.getSize(); i++)
        {
            System.out.println(v.getInfoAt(i));
        }

    }
  
    /**
     * �ж�keyVector�������Ƿ���ڼ�key
     * @param key Ҫ�жϵ�key
     * @return ���keyVector�����д���key�����򷵻�true����������ڣ��򷵻�false
     */
    public boolean isKeyExist(Object key) 
    {
    	return keyVector.contains(key);
	}
}
