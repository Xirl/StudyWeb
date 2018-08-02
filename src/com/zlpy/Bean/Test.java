package com.zlpy.Bean;


import com.zlpy.Dao.ItemPoolDetailsDao;
import com.zlpy.Dao.Impl.ItemPoolDetailsDaoImpl;

public class Test {
@org.junit.Test
	public void te(){
		ItemPoolDetailsDao it=new ItemPoolDetailsDaoImpl();
		PageBean<ItemPoolDetailsBean> page=new PageBean<ItemPoolDetailsBean>();
		page=it.fingBypage(1, 2,null);
		System.out.println(page.getList().size());
		
	}
}
