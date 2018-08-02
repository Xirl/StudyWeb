package com.zlpy.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commom.BaseAction.BaseAction;
import com.commom.Utils.StringUtils;
import com.ljc.entity.User;
import com.zlpy.Bean.ItemPoolDetailsBean;
import com.zlpy.Bean.OptionsBean;
import com.zlpy.Bean.PageBean;
import com.zlpy.Bean.PaperDownload;
import com.zlpy.Bean.QuestionPaperBean;
import com.zlpy.Bean.SolutionBean;
import com.zlpy.Bean.TopicBean;
import com.zlpy.Bean.TopicShow;
import com.zlpy.Bean.TopicTypeBean;
import com.zlpy.Dao.ItemPoolDetailsDao;
import com.zlpy.Dao.OptionsDao;
import com.zlpy.Dao.PaperDownloadDao;
import com.zlpy.Dao.QuestionPaperDao;
import com.zlpy.Dao.SolutionDao;
import com.zlpy.Dao.TopicDao;
import com.zlpy.Dao.TopicTypeDao;
import com.zlpy.Dao.Impl.ItemPoolDetailsDaoImpl;
import com.zlpy.Dao.Impl.OptionsDaoImpl;
import com.zlpy.Dao.Impl.PaperDownloadDaoImpl;
import com.zlpy.Dao.Impl.QuestionPaperDaoImpl;
import com.zlpy.Dao.Impl.SolutionDaoImpl;
import com.zlpy.Dao.Impl.TopicDaoImpl;
import com.zlpy.Dao.Impl.TopicTypeDaoImpl;

public class YAction extends BaseAction {
	/**
	 * �������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String homePage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ItemPoolDetailsDao it = new ItemPoolDetailsDaoImpl();
		PageBean<ItemPoolDetailsBean> page = new PageBean<ItemPoolDetailsBean>();
		Integer pageIndex = StringUtils.str2Integer(
				(String) request.getAttribute("pageIndex"), 1);
		page = it.fingBypage(pageIndex,
				(Integer) request.getAttribute("pageSize"), null);
		request.setAttribute("page", page);
		return SUCCESS;
	}
/**
 * �������
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public String questionPaper(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QuestionPaperDao que = new QuestionPaperDaoImpl();
		int id = StringUtils.str2Integer(request.getParameter("poolId"), 0);
		String questionPaperName = request.getParameter("questionPaperName");
		int userId=StringUtils.str2Integer(request.getParameter("userId"), 0);
		Map<String, Object> map = new HashMap<String, Object>();
		String statu=request.getParameter("statu");
		if (id > 0) {
			map.put("poolId", id);
		}
		if(userId>0){
			map.put("userId", userId);
		}
		PageBean<QuestionPaperBean> page = new PageBean<QuestionPaperBean>();
		Integer pageIndex = StringUtils.str2Integer(
				request.getParameter("pageIndex"), 1);
		if (questionPaperName != null ) {
			if(!"".equals(questionPaperName)){
				questionPaperName = new String(questionPaperName.getBytes("iso-8859-1"),"utf-8");
				map.put("questionPaperName", "%" + questionPaperName + "%");
			}
			
			page = que.fingBypage(pageIndex, StringUtils.str2Integer(request
					.getAttribute("pageSize").toString(), 0), map);
			getJson(request, response, page);
			return null;
		}
		page = que.fingBypage(pageIndex, StringUtils.str2Integer(request
				.getAttribute("pageSize").toString(), 0), map);
		request.setAttribute("page", page);
		if("mySelf".equals(statu)){
			return INPUT;
		}
		return SUCCESS;
	}
/**
 * ������Ŀ
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public String topic(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String status = request.getParameter("status");
		List<TopicTypeBean> lis = new ArrayList<TopicTypeBean>();
		TopicTypeDao type = new TopicTypeDaoImpl();
		List<TopicBean> list = new ArrayList<TopicBean>();
		list=baseTopic(request, response);
		if ("ajax".equals(status)) {
			getJson(request, response, list);
			return null;
		}
		lis = type.findAllType(null);
		request.setAttribute("type", lis);
		return SUCCESS;
	}
/**
 * ��Ŀ��ʾ
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public String topicShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//ʵ����
		List<TopicBean> topicList = new ArrayList<TopicBean>();
		List<TopicTypeBean> typeList = new ArrayList<TopicTypeBean>();
		TopicDao topicDao = new TopicDaoImpl();
		TopicTypeDao typeDao = new TopicTypeDaoImpl();
		OptionsDao optionDao = new OptionsDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> topicMap=new HashMap<String, Object>();
		//��ȡ����
		int id = StringUtils.str2Integer(request.getParameter("queId"), 0);
		map.put("queId", id);
		typeList = typeDao.findQueType("y_topic", map);
		String[] typeAr={"��ѡ��","��ѡ��","�����","�����"};
		List<TopicTypeBean> newTypeList=new ArrayList<TopicTypeBean>();
		for(int i=0;i<typeAr.length;i++){
			for(int j=0;j<typeList.size();j++){
				if(typeList.get(j).getTypeName().equals(typeAr[i])){
					newTypeList.add(typeList.get(j));
				}
			}
		}
		//��˳�������Ŀ
		if (typeList != null && typeList.size() > 0) {
			List<TopicShow> topicShowList = new ArrayList<TopicShow>();
			for(int i=0;i<newTypeList.size();i++){
				map.put("typeId", newTypeList.get(i).getTypeId());
				topicList = topicDao.findAllTopic(map);
				if (topicList != null && topicList.size() > 0) {
					for (int j = 0; j < topicList.size(); j++) {
						TopicShow show = new TopicShow();
						show.setTopic(topicList.get(j));
						map.put("topicId", topicList.get(j).getTopicId());
						show.setList(optionDao.findAllOptions(map));
						SolutionDao solution=new SolutionDaoImpl();
						show.setSolut(solution.findAllSolu(map));
						topicShowList.add(show);
					}
				}
			}
			topicMap.put("type", newTypeList);
			topicMap.put("topic", topicShowList);
		}
		getJson(request, response, topicMap);
		return null;
	}
	/**
	 * ���Ҵ�
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String lookSolu(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
				//ʵ����
				List<TopicTypeBean> typeList = new ArrayList<TopicTypeBean>();
				TopicTypeDao typeDao = new TopicTypeDaoImpl();
				Map<String, Object> map = new HashMap<String, Object>();
				//��ȡ����
				int id = StringUtils.str2Integer(request.getParameter("queId"), 0);
				map.put("queId", id);
				typeList = typeDao.findQueType("y_topic", map);
				String[] typeAr={"��ѡ��","��ѡ��","�����","�����"};
				List<TopicTypeBean> newTypeList=new ArrayList<TopicTypeBean>();
				for(int i=0;i<typeAr.length;i++){
					for(int j=0;j<typeList.size();j++){
						if(typeList.get(j).getTypeName().equals(typeAr[i])){
							newTypeList.add(typeList.get(j));
						}
					}
				}
				getJson(request, response, newTypeList);
		return null;
	}
	/**
	 * ������Ŀ���������
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String topicType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TopicTypeDao typeDao = new TopicTypeDaoImpl();
		List<TopicTypeBean> list = new ArrayList<TopicTypeBean>();
		ItemPoolDetailsDao it = new ItemPoolDetailsDaoImpl();
		List<ItemPoolDetailsBean> listPool=new ArrayList<ItemPoolDetailsBean>();
		Map<String, Object> map=new HashMap<String, Object>();
		String ran=request.getParameter("ran");
		String statu=request.getParameter("statu");
		if(statu!=null &&!"".equals(statu)){
			int id=StringUtils.str2Integer(request.getParameter("queId"), 0);
			if(id>0){
				map.put("queId", id);
			}
			list = typeDao.findQueType("y_topic", map);
			getJson(request, response, list);
			return null;
		}
		listPool = it.findAllPool(map);
		request.setAttribute("pool", listPool);
		if(ran!=null && !"".equals(ran)){
			return INPUT;
		}
		list = typeDao.findAllType(map);
		request.setAttribute("type", list);
		return SUCCESS;
	}
	/**
	 * �����Ŀ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String insertTopic(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		int poolId=StringUtils.str2Integer(request.getParameter("poolName"), 0);
		String paperName=request.getParameter("paperName");
		int queId=0;
		QuestionPaperDao queDao=new QuestionPaperDaoImpl();
		TopicDao topicDao=new TopicDaoImpl();
		if(poolId>0 &&!"".equals(paperName)){
			QuestionPaperBean que=new QuestionPaperBean();
			int userId=((User)request.getSession().getAttribute("currUser")).getUserId();
			que.setUserId(userId);
			que.setDetailsId(poolId);
			paperName = new String(paperName.getBytes("iso-8859-1"),"utf-8");
			que.setQuestionPaperName(paperName);
			queId=queDao.inserPaper(que);
			if(queId>0){
				String[] topicName=request.getParameterValues("topicName");
				String[] topicScore=request.getParameterValues("scoreName");
				String[] topicType=request.getParameterValues("typeName");
				if(topicName!=null && topicName.length>0 &&topicScore!=null 
						&&topicScore.length>0 && topicType!=null && topicType.length>0){
					for(int i=0;i<topicName.length;i++){
						if(!"".equals(topicName[i])&&!"".equals(topicScore[i])
								&&!"".equals(topicType[i])){
							TopicBean topicBean=new TopicBean();
							topicBean.setTypeId(StringUtils.str2Integer(topicType[i], 0));
							topicBean.setQueId(queId);
							topicBean.setScore(StringUtils.str2Double(topicScore[i], 0.0));
							topicName[i] = new String(topicName[i].getBytes("iso-8859-1"),"utf-8");
							topicBean.setTopicName(topicName[i]);
							int topicId=topicDao.insertTopic(topicBean);
							if(topicId>0){
								String[] option=request.getParameterValues("text"+(i+1));
								String[] solu=request.getParameterValues(""+(i+1));
								//ѡ�����
								if(option!=null && option.length>0){
									for(int k=0;k<option.length;k++){
										if(!"".equals(option[k])){
											option[k] = new String(option[k].getBytes("iso-8859-1"),"utf-8");
											OptionsBean optionBean=new OptionsBean();
											OptionsDao optionDao=new OptionsDaoImpl();
											optionBean.setTopicId(topicId);
											optionBean.setOptions(option[k]);
											optionDao.inserOption(optionBean);
										}
									}
								}
								//�����
								if(solu!=null && solu.length>0){
									for(int j=0;j<solu.length;j++){
										if(!"".equals(solu[j])){
											solu[j] = new String(solu[j].getBytes("iso-8859-1"),"utf-8");
											OptionsBean optionBean=new OptionsBean();
											OptionsDao optionDao=new OptionsDaoImpl();
											optionBean.setTopicId(topicId);
											optionBean.setOptions(solu[j]);
											optionDao.inserOption(optionBean);
											//�𰸲���
											inserSolu(solu,queId,topicId,"create");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * ��ѯ�������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findQue(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String queName=request.getParameter("queName");
		Map<String, Object> map=new HashMap<String, Object>();
		QuestionPaperDao queDao=new QuestionPaperDaoImpl();
		List<QuestionPaperBean> list=new ArrayList<QuestionPaperBean>();
		queName = new String(queName.getBytes("iso-8859-1"),"utf-8");
		if(queName!=null && !"".equals(queName)){
			map.put("questionPaperName", "%" + queName + "%");
		}
		list=queDao.findAllQue(map);
		getJson(request, response, list);
		return null;
	}
	/**
	 * ������Ŀ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String topicCount(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
				getJson(request, response, baseTopic(request, response));
				return null;
	}
	/**
	 * ���������Ŀ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String randomTopic(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//��ȡ����
		String array=request.getParameter("array");
		String questionPaperName=request.getParameter("queName");
		questionPaperName = new String(questionPaperName.getBytes("iso-8859-1"),"utf-8");
		int poolId=StringUtils.str2Integer(request.getParameter("poolId"), 0);
		//����ʵ����
		SolutionDao soluDao=new SolutionDaoImpl();
		TopicDao topicDao=new TopicDaoImpl();
		List<TopicBean> topicList=new ArrayList<TopicBean>();
		Map<String, Object> map=new HashMap<String, Object>();
		OptionsDao optionDao=new OptionsDaoImpl();
		Random random=new Random();
		boolean isFalge=true;
		if(array!=null){
			String[] rand=array.split(",");
			int queId=1;
			if(rand!=null && rand.length>0){
				if(poolId>0){
					QuestionPaperDao queDao=new QuestionPaperDaoImpl();
					QuestionPaperBean queBean=new QuestionPaperBean();
					int userId=((User)request.getSession().getAttribute("currUser")).getUserId();
					queBean.setUserId(userId);
					queBean.setQuestionPaperName(questionPaperName);
					queBean.setDetailsId(poolId);
					queId=queDao.inserPaper(queBean);
				}
				if(queId>0){
					for(int i=0;i<rand.length;i++){
						if(rand[i]!=null && !"".equals(rand[i])){
							String[] data=rand[i].split(" ");
							int oldQue=StringUtils.str2Integer(data[0], 0);
							int oldType=StringUtils.str2Integer(data[1], 0);
							int num=StringUtils.str2Integer(data[2], 0);
							map.put("typeId", oldType);
							map.put("queId", oldQue);
							topicList=topicDao.findAllTopic(map);
							int[] math=new int[num];
							//���������Ŀ
							if(num<topicList.size()){
								//����Ĵ���
								for(int j=0;j<num;j++){
									isFalge=true;
									while(isFalge){
										boolean flag=true;
										int count=random.nextInt(topicList.size());
										//�жϸ������Ƿ���ֹ�
										for(int k=0;k<math.length;k++){
											if(math[k]==count){
												flag=false;
											}
										}
										
										if(flag){
											//������Ŀ�����µ����֮��
											TopicBean topicBean=new TopicBean();
											topicBean.setQueId(queId);
											topicBean.setScore(topicList.get(count).getScore());
											topicBean.setTopicName(topicList.get(count).getTopicName());
											topicBean.setTypeId(oldType);
											int topicId=topicDao.insertTopic(topicBean);
											//�жϸ����Ƿ���ѡ��
											map.put("topicId", topicList.get(count).getTopicId());
											int optionCount=optionDao.findOpCount(map);
											if(optionCount>0){
												inserOptions(topicId, map);
											}
											//�ٽ��𰸴���
											List<SolutionBean> soluList=new ArrayList<SolutionBean>();
											soluList=soluDao.findAllSolu(map);
											if(soluList!=null && soluList.size()>0){
												inserSolu(soluList.toArray(),queId,topicId,"new");
											}
											isFalge=false;
											math[j]=count;
										}
									}
								}
							}else{
								for(int j=0;j<topicList.size();j++){
									TopicBean topicBean=new TopicBean();
									topicBean.setQueId(queId);
									topicBean.setScore(topicList.get(j).getScore());
									topicBean.setTopicName(topicList.get(j).getTopicName());
									topicBean.setTypeId(oldType);
									int topicId=topicDao.insertTopic(topicBean);
									//�жϸ����Ƿ���ѡ��
									map.put("topicId", topicList.get(j).getTopicId());
									int optionCount=optionDao.findOpCount(map);
									if(optionCount>0){
										inserOptions(topicId, map);
									}
									//�ٽ��𰸴���
									List<SolutionBean> soluList=new ArrayList<SolutionBean>();
									soluList=soluDao.findAllSolu(map);
									if(soluList!=null && soluList.size()>0){
										inserSolu(soluList.toArray(),queId,topicId,"new");
									}
								}
							}
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	public void inserOptions(int topicId,Map<String, Object> map){
		OptionsDao options=new OptionsDaoImpl();
		List<OptionsBean> optionList=new ArrayList<OptionsBean>();
		optionList=options.findAllOptions(map);
		for(int k=0;k<optionList.size();k++){
			OptionsBean optionBean=new OptionsBean();
			if(optionList.get(k)!=null){
				optionBean.setTopicId(topicId);
				optionBean.setOptions(optionList.get(k).getOptions());
				options.inserOption(optionBean);
			}
		}
	}
	/**
	 * ͨ�õ������𰸵ķ���
	 */
	public void inserSolu(Object[] solution,int queId,int topicId,String statu){
		try {
			if(solution!=null && solution.length>0){
				SolutionDao soluDao=new SolutionDaoImpl();
				if(solution.length>1){
					for(int v=0;v<solution.length;v++){
						SolutionBean soluBean=new SolutionBean();
						soluBean.setQueId(queId);
						soluBean.setTopicId(topicId);
						String solu=null;
						if("create".equals(statu)){
							 solu=(String)solution[v];
							solu = new String(solu.getBytes("iso-8859-1"),"utf-8");
						}else if("new".equals(statu)){
							solu=((SolutionBean)solution[v]).getSolution();
						}
						soluBean.setSolution(solu);
						soluDao.inserSolu(soluBean);
					}
				}else{
					SolutionBean soluBean=new SolutionBean();
					soluBean.setQueId(queId);
					soluBean.setTopicId(topicId);
					String solu=null;
					if("create".equals(statu)){
						 solu=(String)solution[0];
					}else if("new".equals(statu)){
						solu=((SolutionBean)solution[0]).getSolution();
					}
					//solu = new String(solu.getBytes("iso-8859-1"),"utf-8");
					soluBean.setSolution(solu);
					soluDao.inserSolu(soluBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ����������Ŀ��ͨ�÷���
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<TopicBean> baseTopic(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		int queId=StringUtils.str2Integer(request.getParameter("queId"), 0);
		int typeId=StringUtils.str2Integer(request.getParameter("typeId"), 0);
		List<TopicBean> list=new ArrayList<TopicBean>();
		TopicDao topicDao=new TopicDaoImpl();
		Map<String, Object> map=new HashMap<String, Object>();
		if(queId>0){
			map.put("queId", queId);
		}
		if(typeId>0){
			map.put("typeId", typeId);
		}
		list=topicDao.findAllTopic(map);
		return list;
	}
	/**
	 * �����׫д��doc�ļ�
	 * @param request
	 * @param response
	 * @return
	 */
	public String download(HttpServletRequest request,
			HttpServletResponse response){
		String[] oneAr={"һ.","��.","��","��","��"};
		String[] twoAr={"A:","B:","C:","D:","E:","F:"};
		String path=request.getSession().getServletContext().getRealPath("");
		String name=request.getParameter("fileName");
		if(name==null) return null;
		File file=new File(path+"\\zlpy\\doc\\"+name+".doc");
		System.out.println(path+"\\zlpy\\doc\\"+name+".doc");
		try {
			if(!file.exists()){
				file.createNewFile();
//				fileOut.write("����\n".getBytes());
//				fileOut.write("��".getBytes());
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			Map<String, Object> map=new HashMap<String, Object>();
			map=testShow(request,response);
			List<TopicTypeBean> typeList=null;
			List<TopicShow> showList=null;
			if(map!=null && map.size()>0){
				typeList=(List<TopicTypeBean>) map.get("type");
				showList=(List<TopicShow>) map.get("topic");
			}
			boolean isFlag=false;//�ж��Ƿ�׫д���
			int count=1;
			if(typeList!=null && typeList.size()>0
					&&showList!=null &&showList.size()>0){
				for(int i=0;i<typeList.size();i++){
					fileOut.write((oneAr[i]+typeList.get(i).getTypeName()).getBytes());
					fileOut.write("\n".getBytes());
					for(int j=0;j<showList.size();j++){
						if(typeList.get(i).getTypeId()==showList.get(j).getTopic().getTypeId()){
							fileOut.write(((count++)+"."+showList.get(j).getTopic().getTopicName()).getBytes());
							fileOut.write("\n".getBytes());
							if(showList.get(j).getList()!=null && showList.get(j).getList().size()>0){
								for(int k=0;k<showList.get(j).getList().size();k++){
									fileOut.write((twoAr[k]+showList.get(j).getList().get(k).getOptions()).getBytes());
									fileOut.write("\n".getBytes());
								}
							}
						}
					}
				}
				isFlag=true;
			}
			//׫д��ɣ��������ݿⱣ��
			if(isFlag){
				//��֪û�о����ݣ�ֱ�Ӳ���������
				int queId=StringUtils.str2Integer(request.getParameter("queId"), 0);
				PaperDownloadDao paperDao=new PaperDownloadDaoImpl();
				PaperDownload newPaper=new PaperDownload();
				newPaper.setPaperName(queId+".doc");
				newPaper.setQueId(queId);
				paperDao.inserPaper(newPaper);
				getJson(request, response, newPaper);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
 	}
	/**
	 * ͨ�õ���Ŀ��ѯ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> testShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//ʵ����
		List<TopicBean> topicList = new ArrayList<TopicBean>();
		List<TopicTypeBean> typeList = new ArrayList<TopicTypeBean>();
		TopicDao topicDao = new TopicDaoImpl();
		TopicTypeDao typeDao = new TopicTypeDaoImpl();
		OptionsDao optionDao = new OptionsDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> topicMap=new HashMap<String, Object>();
		//��ȡ����
		int id = StringUtils.str2Integer(request.getParameter("queId"), 0);
		map.put("queId", id);
		if(id>0){
			typeList = typeDao.findQueType("y_topic", map);
			String[] typeAr={"��ѡ��","��ѡ��","�����","�����"};
			List<TopicTypeBean> newTypeList=new ArrayList<TopicTypeBean>();
			for(int i=0;i<typeAr.length;i++){
				for(int j=0;j<typeList.size();j++){
					if(typeList.get(j).getTypeName().equals(typeAr[i])){
						newTypeList.add(typeList.get(j));
					}
				}
			}
			//��˳�������Ŀ
			if (typeList != null && typeList.size() > 0) {
				List<TopicShow> topicShowList = new ArrayList<TopicShow>();
				for(int i=0;i<newTypeList.size();i++){
					map.put("typeId", newTypeList.get(i).getTypeId());
					topicList = topicDao.findAllTopic(map);
					if (topicList != null && topicList.size() > 0) {
						for (int j = 0; j < topicList.size(); j++) {
							TopicShow show = new TopicShow();
							show.setTopic(topicList.get(j));
							map.put("topicId", topicList.get(j).getTopicId());
							show.setList(optionDao.findAllOptions(map));
							SolutionDao solution=new SolutionDaoImpl();
							show.setSolut(solution.findAllSolu(map));
							topicShowList.add(show);
						}
					}
				}
				topicMap.put("type", newTypeList);
				topicMap.put("topic", topicShowList);
			}
		}
		return topicMap;
	}
	/**
	 * �༭�µĴ�
	 * @return
	 */
	public String updataSolu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int queId=StringUtils.str2Integer(request.getParameter("queId"), 0);
		TopicDao topicDao=new TopicDaoImpl();
		List<TopicBean> topicList=new ArrayList<TopicBean>();
		Map<String, Object> map=new HashMap<String, Object>();
		SolutionDao soluDao=new SolutionDaoImpl();
		if(queId>0){
			map.put("queId", queId);
			topicList=topicDao.findAllTopic(map);
			if(topicList!=null && topicList.size()>0){
				Integer topicId=0;
				for(int i=0;i<topicList.size();i++){
					topicId=topicList.get(i).getTopicId();
					//���ԭ�д�
					map.put("topicId", topicId);
					soluDao.delectSolu(map);
					//����´�
					String[] solution=request.getParameterValues(topicId.toString());
					for(int j=0;j<solution.length;j++){
						if(solution[j]!=null && !"".equals(solution[j])){
							SolutionBean solu=new SolutionBean();
							solu.setQueId(queId);
							solu.setTopicId(topicId);
							solu.setSolution(solution[j]);
							soluDao.inserSolu(solu);
						}
					}
				}
			}
		}
		return SUCCESS;
	}
}
