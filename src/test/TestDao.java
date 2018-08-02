package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.commom.BaseDao.BaseDao;

public class TestDao extends BaseDao<TestBean>{
@Test
	public void te(){
		String sql="select * from tes";
		List<TestBean> list=new ArrayList<TestBean>();
		list=this.excutQuery(sql, null);
		
		System.out.println(list.size());
	}
}
