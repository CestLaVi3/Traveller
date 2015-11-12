package com.chinasoft.common.utils.aop;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class AopJson {
	public List getJson(){
		List loginList=new ArrayList();
		loginList.add("praiseActiveJoin");
		loginList.add("collectActive");
		loginList.add("saveActiveJoin");
		loginList.add("collectActive");
		loginList.add("findTeamActiveById");
		loginList.add("findActiveJoinById");
		loginList.add("saveTeamActiveJoin");
		loginList.add("saveTeamActive");
		loginList.add("findTeamByUserJoin");
		loginList.add("unJoinActive");
		loginList.add("unCollectActive");
		loginList.add("findTeamByUserCreate");
		loginList.add("findTeamByUserCollect");
		loginList.add("closeActive");
		loginList.add("findInformByUser");
		loginList.add("openActive");
		loginList.add("saveActive");
		loginList.add("deleteCommentById");
		loginList.add("deleteReply");
		loginList.add("saveReply");
		loginList.add("saveComment");
		loginList.add("findCommentById");
		loginList.add("findById");
		loginList.add("praiseStagtegy");
		loginList.add("collectStagtegy");
		loginList.add("deleteStagtegy");
		loginList.add("saveStagtegy");
		loginList.add("updateStagtegy");
		loginList.add("findStagtegyByUser");
		loginList.add("findByUserCollect");
		loginList.add("findstagtegyByCollect");
		loginList.add("findDescById");
		loginList.add("findDescByUser");
		loginList.add("praiseTravelDesc");
		loginList.add("collectTravelDesc");
		loginList.add("saveTravelDesc");
		loginList.add("updateTravelDesc");
		loginList.add("deleteTravelDesc");
		loginList.add("findDescByUserCollect");
		loginList.add("findDescByCollect");
		loginList.add("findCurUserCollect");
		loginList.add("unCollectUser");
		loginList.add("findByCondition");
		loginList.add("collectUser");
		loginList.add("findCurUserFuns");
		loginList.add("findCurUserBrowse");
		loginList.add("findUserByUserId");
		loginList.add("updatePassword");
		loginList.add("updateBaseInfor");
		loginList.add("updateAuthInfor");
		return loginList;
	}
}
