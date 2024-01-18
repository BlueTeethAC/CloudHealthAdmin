package com.LW1.healthConsult.questionaireInfo.mapper;

import java.util.List;
import com.LW1.healthConsult.questionaireInfo.domain.QuestionnaireInfo;

/**
 * 问卷信息Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface QuestionnaireInfoMapper 
{
    /**
     * 查询问卷信息
     * 
     * @param infoId 问卷信息主键
     * @return 问卷信息
     */
    public QuestionnaireInfo selectQuestionnaireInfoByInfoId(Long infoId);

    /**
     * 查询问卷信息列表
     * 
     * @param questionnaireInfo 问卷信息
     * @return 问卷信息集合
     */
    public List<QuestionnaireInfo> selectQuestionnaireInfoList(QuestionnaireInfo questionnaireInfo);

    /**
     * 新增问卷信息
     * 
     * @param questionnaireInfo 问卷信息
     * @return 结果
     */
    public int insertQuestionnaireInfo(QuestionnaireInfo questionnaireInfo);

    /**
     * 修改问卷信息
     * 
     * @param questionnaireInfo 问卷信息
     * @return 结果
     */
    public int updateQuestionnaireInfo(QuestionnaireInfo questionnaireInfo);

    /**
     * 删除问卷信息
     * 
     * @param infoId 问卷信息主键
     * @return 结果
     */
    public int deleteQuestionnaireInfoByInfoId(Long infoId);

    /**
     * 批量删除问卷信息
     * 
     * @param infoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionnaireInfoByInfoIds(Long[] infoIds);
}
