import request from '@/utils/request'

// 查询视频系列列表
export function listSeriesInfo(query) {
  return request({
    url: '/video/seriesInfo/list',
    method: 'get',
    params: query
  })
}

// 查询视频系列详细
export function getSeriesInfo(seriesId) {
  return request({
    url: '/video/seriesInfo/' + seriesId,
    method: 'get'
  })
}

// 新增视频系列
export function addSeriesInfo(data) {
  return request({
    url: '/video/seriesInfo',
    method: 'post',
    data: data
  })
}

// 修改视频系列
export function updateSeriesInfo(data) {
  return request({
    url: '/video/seriesInfo',
    method: 'put',
    data: data
  })
}

// 删除视频系列
export function delSeriesInfo(seriesId) {
  return request({
    url: '/video/seriesInfo/' + seriesId,
    method: 'delete'
  })
}
