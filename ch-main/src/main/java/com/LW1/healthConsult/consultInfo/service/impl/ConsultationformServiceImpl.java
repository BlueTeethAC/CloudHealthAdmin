package com.LW1.healthConsult.consultInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.healthConsult.consultInfo.mapper.ConsultationformMapper;
import com.LW1.healthConsult.consultInfo.domain.Consultationform;
import com.LW1.healthConsult.consultInfo.service.IConsultationformService;

/**
 * 咨询信息Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class ConsultationformServiceImpl implements IConsultationformService 
{
    @Autowired
    private ConsultationformMapper consultationformMapper;

    /**
     * 查询咨询信息
     * 
     * @param ConsultationFormId 咨询信息主键
     * @return 咨询信息
     */
    @Override
    public Consultationform selectConsultationformByConsultationFormId(Long ConsultationFormId)
    {
        return consultationformMapper.selectConsultationformByConsultationFormId(ConsultationFormId);
    }

    /**
     * 查询咨询信息列表
     * 
     * @param consultationform 咨询信息
     * @return 咨询信息
     */
    @Override
    public List<Consultationform> selectConsultationformList(Consultationform consultationform)
    {
        return consultationformMapper.selectConsultationformList(consultationform);
    }

    /**
     * 新增咨询信息
     * 
     * @param consultationform 咨询信息
     * @return 结果
     */
    @Override
    public int insertConsultationform(Consultationform consultationform)
    {
        return consultationformMapper.insertConsultationform(consultationform);
    }

    /**
     * 修改咨询信息
     * 
     * @param consultationform 咨询信息
     * @return 结果
     */
    @Override
    public int updateConsultationform(Consultationform consultationform)
    {
        return consultationformMapper.updateConsultationform(consultationform);
    }

    /**
     * 批量删除咨询信息
     * 
     * @param ConsultationFormIds 需要删除的咨询信息主键
     * @return 结果
     */
    @Override
    public int deleteConsultationformByConsultationFormIds(Long[] ConsultationFormIds)
    {
        return consultationformMapper.deleteConsultationformByConsultationFormIds(ConsultationFormIds);
    }

    /**
     * 删除咨询信息信息
     * 
     * @param ConsultationFormId 咨询信息主键
     * @return 结果
     */
    @Override
    public int deleteConsultationformByConsultationFormId(Long ConsultationFormId)
    {
        return consultationformMapper.deleteConsultationformByConsultationFormId(ConsultationFormId);
    }
}
