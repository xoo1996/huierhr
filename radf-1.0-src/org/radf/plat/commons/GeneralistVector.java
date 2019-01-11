package org.radf.plat.commons;

import java.io.Serializable;
import java.util.Vector;



/**
 * 该类是一个容器类<br>
 * 该类中的数据是按照用户存储的顺序进行存储的。( 注：凡涉及到数字的，均是从0开始记，本模块其它地方不在声明！)
 * 该容器类的特点：
 * <br>
 * <li>该容器中的数据可当一维数据使用，也可当二维数据使用
 * <li>可以确定快速找到数据中指定的键对应的数据，（也可以快速找到数据中指定的值对应的键，条件是键与值必须是一一对应关系）
 * <li>数据的存储按照用户输入的顺序进行存储
 * <li>使用键值对
 * <li>可实现复杂的二维表的数据存储
 * @author wangbaowei
 */
public class GeneralistVector implements Serializable{
	private static final long serialVersionUID = 1L;
    private String ERROR_DATA_MSG = " 请核实输入数据!";
    private String ERROR_EI_MSG = " 中存在错误，请核实后重试";
    
    
//	public int length;

	/** 列数 */
	private int columCount;

	/** 行数 */
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
	 * 在数据中查找第i行列名为XXX的值
	 * 
	 * @param i
	 *            行号 从0开始
	 * @param id
	 *            用户端字段标识(这里指的是列中的字段标识)
	 * @return 返回的是key,可根据该可以获取其他相关信息
	 */
	/* <li> 0 -- 表示无限制，即在所有数据中查找 */

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
				System.err.println("GeneralistVector.getObjectAtRowAndByName() 中出现问题，可能是数据过剩!");
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
	 * 获取列数据对应的键－列对
	 * 该方法操作的对象是第i行
	 * @param i 行号
	 * @param ids 要查找的列的标识的集合,亦即数据库中取过来的数据要赋值与的列名的集合
	 * <br>比如从数据库中取来的数据对应的字段是"B","C","F"，那这里的ids中存储的就是"B","C","F".
	 * @return ___________________________________________________
	 *        |____|_A__|__B__|__C__|__D__|__E__|__F__|__G__|__H__|
	 *        |_M__|_MA_|_MB__|_MC__|_MD__|_ME__|_MF__|_MG__|_MH__|
	 *        |_N__|_NA_|_NB__|_NC__|_ND__|_NE__|_NF__|_NG__|_NH__|
	 *        <br>
	 *        返回值为：(MB,B),(MC,C),(MF,F)的集合,当然这里的i就是0了。                
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
		//一行中所有数据对应的键
		Vector datas = getObjectsAtRowOf(i);

		for (int j = 0; j < datas.size(); j++)
		{
			int k =0;
			while(ids.size()>0)
			{
				//表中的键是XXX\YYY的形式，所以YYY的位置不会是0，故这里使用">"号
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
	 * 获取第i行的所有的键
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
	 * 获取的数据为key，要获取所有信息，可通过该key获取。
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
	 * 获取的数据为key，要获取所有信息，可通过该key获取。
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
	 * 添加数据
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
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
						// System.out.println("GeneralistVector.put() 需要进行测试");
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
					// System.out.println("GeneralistVector.put() 需要进行测试");
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
	 * 删除第i个数据
	 * 
	 * @param i
	 *            序列
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
	 * 删除键值为key的数据
	 * 
	 * @param key
	 *            键值
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
	 * 删除所有数据
	 */
	public void removeAll()
	{
		keyVector.removeAllElements();
		valueVector.removeAllElements();
		vector.removeAllElements();
	}

	/**
	 * 获取容器中数据的个数
	 * 
	 * @return 数据个数
	 */
	public int getSize()
	{
		return keyVector.size();
	}

	/**
	 * 获取第i个数据的key
	 * 
	 * @param i
	 *            序列
	 * @return 键值
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
	 * getStringOfKey 获取第i个数据的key
	 * 
	 * @param i
	 *            序列
	 * @return 键值
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
	 * 获取键值为key的值。
	 * 
	 * @param key
	 * @return 键为key的值
	 */
	public Object getValueOfKey(Object key)
	{
		int i = keyVector.indexOf(key);
		if (i < 0)
		{
			System.err.println("键 " + key + "  不存在,请核实您输入的键值正确性!");
			return null;
		}
		return ((Entity) vector.get(i)).getValue();
	}
	/**
	 * 获取键值为key的值。
	 * 
	 * @param key
	 * @return 键为key的值为字符串
	 */
	public String getVOfKBySting(Object key)
	{
		return getValueOfKey(key).toString();
	}

	/**
	 * 获取值为value的键,该方法仅在一对一的情况下是正确的（即任意键的值都不相同）。
	 * 
	 * @param value
	 * @return 值为value的键
	 */
	public Object getKeyOfValue(Object value)
	{
		int i = valueVector.indexOf(value);
		if (i < 0)
		{
			System.err.println("键 " + value + "  不存在,请核实您输入的键值正确性!");
			return null;
		}
		return ((Entity) vector.get(i)).getKey();
	}

	/**
	 * 获取第i个数据的Value
	 * 
	 * @param i
	 *            序列
	 * @return 获取第i个数据的Value
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
	 * 获取第i个数据的信息
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
	 * 内部对象
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
	 * 设定列数，实现把该容器中存储的数据可视为二维数据。
	 * @param columCount
	 */
	public void setcolumCount(int columCount)
	{
		this.columCount = columCount;
	}
	
	/**
	 * 设定行数和列数
	 * <br>把一维数据转换成二维数据
	 * @param r 行数
	 * @param c 列数
	 */
	public void setRowCountAndColumCount(int r,int c)
	{
		this.rowCount=r;
		this.columCount=c;
	}

	/**
	 * 行数
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
         * 放入数据
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
        v.put("bb阿 ", "1");
        v.put("bb", "3");
        System.out.println("v的大小 " + v.getSize());
        for (int i = 0; i < v.getSize(); i++)
        {
            System.out.println(v.getInfoAt(i));
        }

    }
  
    /**
     * 判断keyVector容器中是否存在键key
     * @param key 要判断的key
     * @return 如果keyVector容器中存在key键，则返回true，如果不存在，则返回false
     */
    public boolean isKeyExist(Object key) 
    {
    	return keyVector.contains(key);
	}
}
