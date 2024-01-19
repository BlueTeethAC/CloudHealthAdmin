import request from '@/utils/request'

// 查询线下活动列表
export function listActivityInfo(query) {
  return request({
    url: '/activity/activityInfo/list',
    method: 'get',
    params: query
  })
}

// 查询线下活动详细
export function getActivityInfo(activityId) {
  return request({
    url: '/activity/activityInfo/' + activityId,
    method: 'get'
  })
}

// 新增线下活动
export function addActivityInfo(data) {
  return request({
    url: '/activity/activityInfo',
    method: 'post',
    data: data
  })
}

// 修改线下活动
export function updateActivityInfo(data) {
  return request({
    url: '/activity/activityInfo',
    method: 'put',
    data: data
  })
}

// 删除线下活动
export function delActivityInfo(activityId) {
  return request({
    url: '/activity/activityInfo/' + activityId,
    method: 'delete'
  })
}
