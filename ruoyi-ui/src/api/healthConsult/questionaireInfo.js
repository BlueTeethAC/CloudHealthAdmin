import request from '@/utils/request'

// 查询问卷信息列表
export function listQuestionaireInfo(query) {
  return request({
    url: '/healthConsult/questionaireInfo/list',
    method: 'get',
    params: query
  })
}

// 查询问卷信息详细
export function getQuestionaireInfo(infoId) {
  return request({
    url: '/healthConsult/questionaireInfo/' + infoId,
    method: 'get'
  })
}

// 新增问卷信息
export function addQuestionaireInfo(data) {
  return request({
    url: '/healthConsult/questionaireInfo',
    method: 'post',
    data: data
  })
}

// 修改问卷信息
export function updateQuestionaireInfo(data) {
  return request({
    url: '/healthConsult/questionaireInfo',
    method: 'put',
    data: data
  })
}

// 删除问卷信息
export function delQuestionaireInfo(infoId) {
  return request({
    url: '/healthConsult/questionaireInfo/' + infoId,
    method: 'delete'
  })
}
