package com.LW1.healthConsult.consultInfo.service;

import java.util.List;
import com.LW1.healthConsult.consultInfo.domain.Consultationform;

/**
 * 咨询信息Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface IConsultationformService 
{
    /**
     * 查询咨询信息
     * 
     * @param ConsultationFormId 咨询信息主键
     * @return 咨询信息
     */
    public Consultationform selectConsultationformByConsultationFormId(Long ConsultationFormId);

    /**
     * 查询咨询信息列表
     * 
     * @param consultationform 咨询信息
     * @return 咨询信息集合
     */
    public List<Consultationform> selectConsultationformList(Consultationform consultationform);

    /**
     * 新增咨询信息
     * 
     * @param consultationform 咨询信息
     * @return 结果
     */
    public int insertConsultationform(Consultationform consultationform);

    /**
     * 修改咨询信息
     * 
     * @param consultationform 咨询信息
     * @return 结果
     */
    public int updateConsultationform(Consultationform consultationform);

    /**
     * 批量删除咨询信息
     * 
     * @param ConsultationFormIds 需要删除的咨询信息主键集合
     * @return 结果
     */
    public int deleteConsultationformByConsultationFormIds(Long[] ConsultationFormIds);

    /**
     * 删除咨询信息信息
     * 
     * @param ConsultationFormId 咨询信息主键
     * @return 结果
     */
    public int deleteConsultationformByConsultationFormId(Long ConsultationFormId);
}
