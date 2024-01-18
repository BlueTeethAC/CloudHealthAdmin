import request from '@/utils/request'

// 查询资讯信息反馈列表
export function listFeedBackInfo(query) {
  return request({
    url: '/healthConsult/feedBackInfo/list',
    method: 'get',
    params: query
  })
}

// 查询资讯信息反馈详细
export function getFeedBackInfo(feedbackinfoId) {
  return request({
    url: '/healthConsult/feedBackInfo/' + feedbackinfoId,
    method: 'get'
  })
}

// 新增资讯信息反馈
export function addFeedBackInfo(data) {
  return request({
    url: '/healthConsult/feedBackInfo',
    method: 'post',
    data: data
  })
}

// 修改资讯信息反馈
export function updateFeedBackInfo(data) {
  return request({
    url: '/healthConsult/feedBackInfo',
    method: 'put',
    data: data
  })
}

// 删除资讯信息反馈
export function delFeedBackInfo(feedbackinfoId) {
  return request({
    url: '/healthConsult/feedBackInfo/' + feedbackinfoId,
    method: 'delete'
  })
}
