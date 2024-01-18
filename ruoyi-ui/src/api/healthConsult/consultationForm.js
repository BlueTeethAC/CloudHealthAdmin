import request from '@/utils/request'

// 查询咨询信息列表
export function listConsultationForm(query) {
  return request({
    url: '/healthConsult/consultationForm/list',
    method: 'get',
    params: query
  })
}

// 查询咨询信息详细
export function getConsultationForm(ConsultationFormId) {
  return request({
    url: '/healthConsult/consultationForm/' + ConsultationFormId,
    method: 'get'
  })
}

// 新增咨询信息
export function addConsultationForm(data) {
  return request({
    url: '/healthConsult/consultationForm',
    method: 'post',
    data: data
  })
}

// 修改咨询信息
export function updateConsultationForm(data) {
  return request({
    url: '/healthConsult/consultationForm',
    method: 'put',
    data: data
  })
}

// 删除咨询信息
export function delConsultationForm(ConsultationFormId) {
  return request({
    url: '/healthConsult/consultationForm/' + ConsultationFormId,
    method: 'delete'
  })
}
