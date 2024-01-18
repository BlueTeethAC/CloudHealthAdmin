package com.LW1.healthConsult.questionaireInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.healthConsult.questionaireInfo.mapper.QuestionnaireInfoMapper;
import com.LW1.healthConsult.questionaireInfo.domain.QuestionnaireInfo;
import com.LW1.healthConsult.questionaireInfo.service.IQuestionnaireInfoService;

/**
 * 问卷信息Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class QuestionnaireInfoServiceImpl implements IQuestionnaireInfoService 
{
    @Autowired
    private QuestionnaireInfoMapper questionnaireInfoMapper;

    /**
     * 查询问卷信息
     * 
     * @param infoId 问卷信息主键
     * @return 问卷信息
     */
    @Override
    public QuestionnaireInfo selectQuestionnaireInfoByInfoId(Long infoId)
    {
        return questionnaireInfoMapper.selectQuestionnaireInfoByInfoId(infoId);
    }

    /**
     * 查询问卷信息列表
     * 
     * @param questionnaireInfo 问卷信息
     * @return 问卷信息
     */
    @Override
    public List<QuestionnaireInfo> selectQuestionnaireInfoList(QuestionnaireInfo questionnaireInfo)
    {
        return questionnaireInfoMapper.selectQuestionnaireInfoList(questionnaireInfo);
    }

    /**
     * 新增问卷信息
     * 
     * @param questionnaireInfo 问卷信息
     * @return 结果
     */
    @Override
    public int insertQuestionnaireInfo(QuestionnaireInfo questionnaireInfo)
    {
        return questionnaireInfoMapper.insertQuestionnaireInfo(questionnaireInfo);
    }

    /**
     * 修改问卷信息
     * 
     * @param questionnaireInfo 问卷信息
     * @return 结果
     */
    @Override
    public int updateQuestionnaireInfo(QuestionnaireInfo questionnaireInfo)
    {
        return questionnaireInfoMapper.updateQuestionnaireInfo(questionnaireInfo);
    }

    /**
     * 批量删除问卷信息
     * 
     * @param infoIds 需要删除的问卷信息主键
     * @return 结果
     */
    @Override
    public int deleteQuestionnaireInfoByInfoIds(Long[] infoIds)
    {
        return questionnaireInfoMapper.deleteQuestionnaireInfoByInfoIds(infoIds);
    }

    /**
     * 删除问卷信息信息
     * 
     * @param infoId 问卷信息主键
     * @return 结果
     */
    @Override
    public int deleteQuestionnaireInfoByInfoId(Long infoId)
    {
        return questionnaireInfoMapper.deleteQuestionnaireInfoByInfoId(infoId);
    }
}
