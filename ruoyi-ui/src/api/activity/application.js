import request from '@/utils/request'

// 查询活动报名列表
export function listApplication(query) {
  return request({
    url: '/activity/application/list',
    method: 'get',
    params: query
  })
}

// 查询活动报名详细
export function getApplication(infoId) {
  return request({
    url: '/activity/application/' + infoId,
    method: 'get'
  })
}

// 新增活动报名
export function addApplication(data) {
  return request({
    url: '/activity/application',
    method: 'post',
    data: data
  })
}

// 修改活动报名
export function updateApplication(data) {
  return request({
    url: '/activity/application',
    method: 'put',
    data: data
  })
}

// 删除活动报名
export function delApplication(infoId) {
  return request({
    url: '/activity/application/' + infoId,
    method: 'delete'
  })
}
