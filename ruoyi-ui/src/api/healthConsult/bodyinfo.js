import request from '@/utils/request'

// 查询用户个人身体信息列表
export function listBodyinfo(query) {
  return request({
    url: '/healthConsult/bodyinfo/list',
    method: 'get',
    params: query
  })
}

// 查询用户个人身体信息详细
export function getBodyinfo(infoId) {
  return request({
    url: '/healthConsult/bodyinfo/' + infoId,
    method: 'get'
  })
}

// 新增用户个人身体信息
export function addBodyinfo(data) {
  return request({
    url: '/healthConsult/bodyinfo',
    method: 'post',
    data: data
  })
}

// 修改用户个人身体信息
export function updateBodyinfo(data) {
  return request({
    url: '/healthConsult/bodyinfo',
    method: 'put',
    data: data
  })
}

// 删除用户个人身体信息
export function delBodyinfo(infoId) {
  return request({
    url: '/healthConsult/bodyinfo/' + infoId,
    method: 'delete'
  })
}
