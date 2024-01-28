import request from '@/utils/request'

// 查询发布活动列表
export function listMyActivityInfo(query) {
  return request({
    url: '/activity/activityInfo/list',
    method: 'get',
    params: query
  })
}

// 查询发布活动详细
export function getMyActivityInfo(activityId) {
  return request({
    url: '/activity/activityInfo/' + activityId,
    method: 'get'
  })
}

// 新增发布活动
export function addMyActivityInfo(data) {
  return request({
    url: '/activity/activityInfo',
    method: 'post',
    data: data
  })
}

// 修改发布活动
export function updateMyActivityInfo(data) {
  return request({
    url: '/activity/activityInfo',
    method: 'put',
    data: data
  })
}

// 删除发布活动
export function delMyActivityInfo(activityId) {
  return request({
    url: '/activity/activityInfo/' + activityId,
    method: 'delete'
  })
}
