package com.people.dptwb.manager.dataanalysis;

import com.people.dptwb.manager.TBaseManager;

public class DataAnalysisManager extends TBaseManager {
	/**
	private static final Logger logger = Logger.getLogger(DataAnalysisManager.class);
	
	private static final String getuseridlistsql="select * from zwwbgl_third_bind_info where third_code!=13 and (band_time+expiry_time>? or third_code=10) ";
	public List<ZwwbglThirdSetVO> getThirdSetVO(){
		List<ZwwbglThirdSetVO> uservolist= new ArrayList<ZwwbglThirdSetVO>();
		Connection readconn =null;
		try {
			readconn = getRead2Connection();
			
			uservolist = (List<ZwwbglThirdSetVO>) queryEntityList(readconn, getuseridlistsql, ZwwbglThirdSetVO.class,UtilTools.getTimestamp());
//			long now =UtilTools.getTimestamp();
//			for(int i=0;i<uservolist.size();i++){
//				if(uservolist.get(i).getExpiryTime()+uservolist.get(i).getBandTime()>now){
//					//if(!"10".equals(uservolist.get(i).getThirdCode())){
//						uservolist.remove(i);
//					//}
//				}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("zwwbgl_third_bind_info ()"+e);
		}finally{
			closeConnection(readconn);
		}
		
		return uservolist;
		
	}
	
	
	private static final String getuserlistbyuseridsql="select * from zwwbgl_third_bind_info where third_user_id=? ";
	public ZwwbglThirdSetVO getThirdSetVO(String userId){
		ZwwbglThirdSetVO uservo=null;
		Connection readconn =null;
		try {
			readconn = getRead2Connection();
			
			uservo = (ZwwbglThirdSetVO) queryEntity(readconn, getuserlistbyuseridsql, ZwwbglThirdSetVO.class,userId);
//			long now =UtilTools.getTimestamp();
//			for(int i=0;i<uservolist.size();i++){
//				if(uservolist.get(i).getExpiryTime()+uservolist.get(i).getBandTime()>now){
//					//if(!"10".equals(uservolist.get(i).getThirdCode())){
//						uservolist.remove(i);
//					//}
//				}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("getThirdSetVO (userId)"+e);
		}finally{
			closeConnection(readconn);
		}
		
		return uservo;
		
	}
	
	
	
	
	
	private static final String analysisSql="select * from zwwbgl_analysis_data where user_id=? and posttime>? and posttime<?";
	public List<Zwwbgl_analysis_data> getAnalysisData(ZwwbglAnalysisParameter parameter){
		List<Zwwbgl_analysis_data> analysisdatalist= new ArrayList<Zwwbgl_analysis_data>();
		Connection conn = null;
		try {
			conn = getRead2Connection();
			analysisdatalist=(List<Zwwbgl_analysis_data>)queryEntityList(conn, analysisSql, new Zwwbgl_analysis_data(),parameter.getUser_id(),parameter.getTime_begin(),parameter.getTime_end())	;
		if(analysisdatalist!=null&&analysisdatalist.size()>0){
			for(int i=0;i<analysisdatalist.size();i++){
				analysisdatalist.get(i).setId(i);
				analysisdatalist.get(i).setDatatime(UtilTools.getDisplayTimeToDate(analysisdatalist.get(i).getPosttime()));
		}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("获取数值分析出现错误getAnalysisData()"+e);
		}finally{
			closeConnection(conn);
		}	
		return analysisdatalist;
	}
	
	
	private static final String updateAnalysisSql="insert into zwwbgl_analysis_data(user_id,follower_num,msg_num,reply_num,posttime,atme_num,data_time) values(?,?,?,?,?,?,now())";
	public  int updateAnalysisInfo(List<ZwwbglThirdSetVO> uservo){
        Connection conn =null;
        List<Zwwbgl_analysis_data> listdata =new ArrayList<Zwwbgl_analysis_data>();
        int resmsg=0;
		try {
			conn = getMainConnection();
			setAutoCommit(conn, true);
			PreparedStatement pst=null;
				pst = conn.prepareStatement(updateAnalysisSql);
				int tasksize =0;
				if(uservo!=null&&uservo.size()>0){
					tasksize =uservo.size();
				}
				ExecutorService pool = Executors.newSingleThreadExecutor();
				List<Future<Zwwbgl_analysis_data>> list = new ArrayList<Future<Zwwbgl_analysis_data>>(tasksize);
				 // 执行任务并获取Future对象
				for(ZwwbglThirdSetVO thirdvo:uservo){
					list.add(pool.submit(new FetchTwitterForAnalysis(thirdvo)));
					
				}
				
				pool.shutdown();
				for( Future<Zwwbgl_analysis_data> fu:list){
					Zwwbgl_analysis_data analysisdata= fu.get();
					if(analysisdata!=null){
						listdata.add(analysisdata);
					}
				 }
				for(Zwwbgl_analysis_data ad:listdata){
					pst.setString(1, ad.getUserId());
					pst.setInt(2, ad.getFollowerNum());
					pst.setInt(3, ad.getMsgNum());
					pst.setInt(4, Integer.valueOf(String.valueOf(ad.getReplyNum())));
					pst.setLong(5, UtilTools.getTimestamp());
					pst.setInt(6, Integer.valueOf(String.valueOf(ad.getAtmeNum())));
					pst.addBatch();
				}
				pst.executeBatch();
			    pst.close();
				resmsg=1;
			} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("更新分析数据出现错误updateAnalysisInfo()"+e);
		}
		
		return resmsg;
	}
	
	private static final String analysisSqlinfo="select * from zwwbgl_analysis_data where user_id=? order by posttime  desc limit 1";
	public Zwwbgl_analysis_data getAnalysisDataInfo(ZwwbglAnalysisParameter parameter){
		Zwwbgl_analysis_data analysisdatainfo= null;
		Connection conn = null;
		try {
			conn = getRead2Connection();
			analysisdatainfo=(Zwwbgl_analysis_data)queryEntity(conn, analysisSqlinfo, Zwwbgl_analysis_data.class,parameter.getUser_id())	;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("获取数值分析出现错误getAnalysisDataInfo()"+e);
		}finally{
			closeConnection(conn);
		}	
		return analysisdatainfo;
	}
	
	public Zwwbgl_analysis_data getAnalysusData(ZwwbglAnalysisParameter parameter){
		
		return fetchUserData( getThirdSetVO(parameter.getUser_id()));
		
		
	}
	
	
	
	public Zwwbgl_analysis_data fetchUserData(ZwwbglThirdSetVO userthirdvo){
		   UserInfoAjax userinfo=new UserInfoAjax();
		   UserCommentsSina sinacomment =null;
	        TimeLineSina sinaAtme=null;
	        TimeLinePeople peopleAtme=null;
		ZwwbptParameter parameter = new ZwwbptParameter();
		parameter.setThirdCode(userthirdvo.getThirdCode());
		parameter.setAccess_token(userthirdvo.getAccessToken());
		parameter.setUserId(userthirdvo.getThirdUserId());
		parameter.setOtherUserId(userthirdvo.getThirdUserId());
		parameter.setOpen_id(userthirdvo.getThirdUserId());
		parameter.setNickName(userthirdvo.getThirdNickName());
		parameter.setUserName(userthirdvo.getUserName());
		parameter.setPassword(userthirdvo.getThirdPassword());
		parameter.setAccountId(userthirdvo.getAccountId());
		ZwwbglThirdUsers thirduser=userinfo.getUserInfo(parameter);
		Zwwbgl_analysis_data analysisdata=null;
		if(thirduser!=null){
		analysisdata = new Zwwbgl_analysis_data();
		analysisdata.setUserId(userthirdvo.getThirdUserId());
		analysisdata.setFollowerNum(thirduser.getFollowerNum());
		analysisdata.setMsgNum(thirduser.getMsgNum());
	    if(parameter.getThirdCode()==DBValueConstant.THIRD_CODE_SINA){
	    	sinacomment= new UserCommentsSina();
	    	sinaAtme = new TimeLineSina();
	    	if(sinaAtme!=null&&sinacomment!=null){
	    	analysisdata.setReplyNum(Integer.parseInt(String.valueOf(sinacomment.getTotalComments(parameter))));
	    	analysisdata.setAtmeNum(Integer.parseInt(String.valueOf(sinaAtme.getMentionsTimeLineTotal(parameter))));
	    	}
	    }
	    else if(parameter.getThirdCode()==DBValueConstant.THIRD_CODE_PEOPLE){
	    	analysisdata.setReplyNum(thirduser.getReplyNum());
	    	peopleAtme = new TimeLinePeople();
	    	if(peopleAtme!=null){
	    	analysisdata.setAtmeNum(Integer.parseInt(String.valueOf(peopleAtme.getMentionsTimeLineTotal(parameter))));
	    	}
	    }
		}
		return analysisdata;
	}
    // 更新每日统计数据
	private static final String updatesql="insert into zwwbgl_analysis(msg_num,account_num,msg_num_all,account_num_all,account_login_num,msg_account_num,posttime,user_num,user_all_num,msg_user_num,add_time) values(?,?,?,?,?,?,?,?,?,?,now())";
	//每日登陆用户数
	private static final String loginsql ="select count(user_id) as loginnum from zwwbgl_users where last_login>? and last_login<?";
	private static final long timestatic =60*60*24;//一天的秒数
	public void setMsgAndAccountData(){
		StringBuilder msgsql = new StringBuilder();
		StringBuilder accountsql = new StringBuilder();
		StringBuilder msgaccountsql = new StringBuilder();
		StringBuilder usersql = new StringBuilder();
		StringBuilder userallsql = new StringBuilder();
		StringBuilder msgusersql = new StringBuilder();
		
		//每日发送微博用户数量
		msgusersql.append("select count(zu.user_id) as msgusernum from zwwbgl_users zu ,(select DISTINCT user_id  from zwwbgl_accounts za ,  (select DISTINCT account_id from zwwbgl_shared_msg_logs where add_time>? and add_time<?) b WHERE za.account_id=b.account_id) c where zu.user_id=c.user_id");
		//总用户数
		usersql.append("select count(user_id) as userNum from zwwbgl_users ");
		//每日新增用户数
		userallsql.append("select count(user_id) as userNum from zwwbgl_users where signupdate>? and signupdate<?");
		
		//每日新增的微博数
		msgsql.append("select count(distinct content_id) as msgNum from zwwbgl_shared_msg_logs");
		//每日新增的组账号数量
		accountsql.append("select count(distinct account_id) as accountNum from zwwbgl_third_set_new");
		//每日发微博的组账号数
		msgaccountsql.append("select count(distinct account_id) as msgaccountNum from zwwbgl_shared_msg_logs");
		
		Connection conn = null;
		Connection mainconn=null;
		Map<String ,Object> msgmap=null;
		Map<String ,Object> accountmap=null;
		Map<String ,Object> msgaccountmap=null;
		Map<String ,Object> msgallmsgmap=null;
		Map<String ,Object> accountallmsgmap=null;
		Map<String ,Object> loginnummap=null;
		Map<String ,Object> usermap=null;
		Map<String ,Object> msgusermap=null;
		long begintime =UtilTools.getTimestamp()-timestatic;
		long endtime = UtilTools.getTimestamp();
		int msgnum=0,accountnum=0,msgaccountnum=0,msgnumall=0,accountnumall=0,loginnum=0,usernum=0,userallnum=0,msgusernum=0;
		try {
			conn= getRead2Connection();
			accountsql.append(" where band_time<?");
			accountallmsgmap=queryMap(conn, accountsql.toString(),endtime);
			if(accountallmsgmap!=null&&accountallmsgmap.size()>0){
				accountnumall = ((Long)accountallmsgmap.get("accountNum")).intValue();
			}
			msgsql.append("  where add_time <?");
			msgallmsgmap=queryMap(conn, msgsql.toString(),endtime);
			if(msgallmsgmap!=null&&msgallmsgmap.size()>0){
				msgnumall =((Long)msgallmsgmap.get("msgNum")).intValue();
			}
			
			msgsql.append(" and add_time>?");
			msgmap=queryMap(conn, msgsql.toString(),endtime,begintime);
			if(msgmap!=null&&msgmap.size()>0){
				msgnum =((Long)msgmap.get("msgNum")).intValue();
			}
			accountsql.append(" and band_time>?");
			accountmap=queryMap(conn, accountsql.toString(),endtime,begintime);
			if(accountmap!=null&&accountmap.size()>0){
				accountnum = ((Long)accountmap.get("accountNum")).intValue();
			}
			loginnummap=queryMap(conn, loginsql, begintime,endtime);
			if(loginnummap!=null&&loginnummap.size()>0){
				loginnum=((Long)loginnummap.get("loginnum")).intValue();
			}
			msgaccountsql.append("  where add_time>?").append(" and add_time<?");
			msgaccountmap=queryMap(conn, msgaccountsql.toString(),begintime,endtime);
			if(msgaccountmap!=null&&msgaccountmap.size()>0){
				msgaccountnum =((Long)msgaccountmap.get("msgaccountNum")).intValue();
			}
			usersql.append(" where signupdate<?");
			usermap=queryMap(conn, usersql.toString(),endtime);
			if(usermap!=null&&usermap.size()>0){
				userallnum=((Long)usermap.get("usernum")).intValue();
			}
			usersql.append(" and signupdate>?  ");
			usermap=queryMap(conn, usersql.toString(), endtime,begintime);
			if(usermap!=null&&usermap.size()>0){
				usernum=((Long)usermap.get("usernum")).intValue();
			}
			msgusermap=queryMap(conn,msgusersql.toString(),begintime,endtime);
			if(msgusermap!=null&&msgusermap.size()>0){
				msgusernum=((Long)msgusermap.get("msgusernum")).intValue();
			}
			
			mainconn=getMainConnection();
			update(mainconn,updatesql, msgnum,accountnum,msgnumall,accountnumall,loginnum,msgaccountnum,endtime,usernum,userallnum,msgusernum);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("setMsgAndAccountData()"+e);
		}finally{
			closeConnection(mainconn);
			closeConnection(conn);
		}
		
		
	}
	public static final String getdatasql="select * from zwwbgl_analysis where posttime>? and posttime<? order by posttime asc";
	public List<ZwwbglMsgAndAccountData> getMsgAndAccountData(long begintime,long endtime){
		List<ZwwbglMsgAndAccountData> datalist=null;
		Connection conn=null;
		try {
			conn=getRead3Connection();
			datalist=(List<ZwwbglMsgAndAccountData>) queryEntityList(conn, getdatasql, new ZwwbglMsgAndAccountData(), begintime,endtime);
		
			if(datalist!=null&&datalist.size()>0){
				for(int i=0;i<datalist.size();i++){
					datalist.get(i).setId(i);
					datalist.get(i).setAddTime(UtilTools.getDisplayTimeToDate(datalist.get(i).getPosttime()));
			}
				}
		
		} catch (Exception e) {
			logger.error("getMsgAndAccountData()"+e);
		}finally{
			closeConnection(conn);
		}
		
		return datalist;
	}
	 * */
}
