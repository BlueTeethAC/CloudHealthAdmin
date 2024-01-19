import request from '@/utils/request'

// 查询活动分类列表
export function listActivityClassify(query) {
  return request({
    url: '/activity/activityClassify/list',
    method: 'get',
    params: query
  })
}

// 查询活动分类详细
export function getActivityClassify(classifyId) {
  return request({
    url: '/activity/activityClassify/' + classifyId,
    method: 'get'
  })
}

// 新增活动分类
export function addActivityClassify(data) {
  return request({
    url: '/activity/activityClassify',
    method: 'post',
    data: data
  })
}

// 修改活动分类
export function updateActivityClassify(data) {
  return request({
    url: '/activity/activityClassify',
    method: 'put',
    data: data
  })
}

// 删除活动分类
export function delActivityClassify(classifyId) {
  return request({
    url: '/activity/activityClassify/' + classifyId,
    method: 'delete'
  })
}
