import request from '@/utils/request'

// 查询系列订阅列表
export function listSeriesSubscribe(query) {
  return request({
    url: '/video/seriesSubscribe/list',
    method: 'get',
    params: query
  })
}

// 查询系列订阅详细
export function getSeriesSubscribe(id) {
  return request({
    url: '/video/seriesSubscribe/' + id,
    method: 'get'
  })
}

// 新增系列订阅
export function addSeriesSubscribe(data) {
  return request({
    url: '/video/seriesSubscribe',
    method: 'post',
    data: data
  })
}

// 修改系列订阅
export function updateSeriesSubscribe(data) {
  return request({
    url: '/video/seriesSubscribe',
    method: 'put',
    data: data
  })
}

// 删除系列订阅
export function delSeriesSubscribe(id) {
  return request({
    url: '/video/seriesSubscribe/' + id,
    method: 'delete'
  })
}
