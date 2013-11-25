package com.insigma.guobo.tbk.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.insigma.guobo.tbk.pojo.Comments;
import com.insigma.guobo.tbk.pojo.Goods;
import com.insigma.guobo.tbk.pojo.HibernateSessionFactory;
import com.insigma.guobo.tbk.pojo.Users;
import com.insigma.guobo.tbk.vo.CommentsVo;

public class CommentsDaoImpl implements CommentsDao {

	//�ָ���������ѯ���б�  Ȼ���ڶ��б����ͳ��
	//ֱ�������ݿ��в�ѯ

	@SuppressWarnings("unchecked")
	public List<CommentsVo> getList(String nick, String goodName, String time1,String time2) {
		List<CommentsVo> list = new ArrayList<CommentsVo>();
		Session s = HibernateSessionFactory.getSession();
		//��ȷ�� �û�id ��Ʒid
		int userId = -1;
		int goodId = -1;
		if(nick != ""){
			List<Users> list1 = s.createQuery("from Users where nick='" + nick + "'").list();
			if(null != list1 && list1.size() != 0){
				userId = list1.get(0).getId();
			}
		}
		if(goodName != ""){
			List<Goods> list1 = s.createQuery("from Goods where name='" + goodName + "'").list();
			if(null != list1 && list1.size() != 0){
				goodId = list1.get(0).getId();
			}
		}
		
		CommentsVo cv1 = new CommentsVo();
		List<CommentsVo> list2 = new ArrayList<CommentsVo>();
		StringBuilder HQL = new StringBuilder();//,
		HQL.append("select new com.insigma.guobo.tbk.vo.CommentsVo(c.id,u.nick,g.name,c.content,c.time,c.level) from Users u,Goods g,Comments c where c.users.id=u.id and c.goods.id=g.id ");
		if(userId != -1){
			HQL.append("AND u.id=" + userId + " ");
		}
		if(goodId != -1){
			HQL.append("and g.id=" + goodId + " ");
		}
		//��ѯĳһʱ���ε�������  �����һ��ʱ��Ϊ"" ����ǰ̨����Ĭ�ϵ�ʱ��Ϊ��ǰʱ��
		if(time1 != "" && time2 != ""){
			HQL.append("and c.time between'" + time1 +"' and '"+ time2 +"'");
		}
		
		Query q = s.createQuery(HQL.toString());
		list2 = q.list();
		//ͳ��������  ������ ������
		//
		if(list2.size() != 0 ){
			
			int goodLevelCount = 0;
			int badLevelCount = 0;
			for (CommentsVo cv : list2) {
				if(null != cv.getBadLevel() && 1 == cv.getBadLevel()){
					badLevelCount++;
				}
				if(null != cv.getGoodLevel() && 1 == cv.getGoodLevel()){
					goodLevelCount++;
				}
			}
			//������
			cv1.setUserName(nick);
			cv1.setGoodName(goodName);
			cv1.setTotal(list2.size());
			cv1.setGoodLevel(goodLevelCount);
			cv1.setBadLevel(badLevelCount);
			cv1.setMidLevel(cv1.getTotal() - goodLevelCount - badLevelCount);
			//������ ������
			cv1.setBadRate((double)badLevelCount/cv1.getTotal());
			cv1.setGoodRate((double)goodLevelCount/cv1.getTotal());
			//�Ȱ�cv1  (ͳ�ƽ��)  �ŵ� list��
			list.add(cv1);
			list.addAll(list2);
			
		}

		return list;
	}

	public boolean delCommentById(int cId) {
		boolean flag = false;
		Comments c = new Comments();
		c.setId(cId);
		Session s = HibernateSessionFactory.getSession();
		try{
			s.beginTransaction();
			s.createQuery("delete from Comments where id=" + cId).executeUpdate();
			//s.delete(c);
			s.flush();
			s.getTransaction().commit();
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			flag = false;
		}
		return flag;
	}

}
