import request from '@/utils/request'

// 查询我的视频系列列表
export function listMySeriesInfo(query) {
  return request({
    url: '/video/seriesInfo/list',
    method: 'get',
    params: query
  })
}

// 查询我的视频系列详细
export function getMySeriesInfo(seriesId) {
  return request({
    url: '/video/seriesInfo/' + seriesId,
    method: 'get'
  })
}

// 新增我的视频系列
export function addMySeriesInfo(data) {
  return request({
    url: '/video/seriesInfo',
    method: 'post',
    data: data
  })
}

// 修改我的视频系列
export function updateMySeriesInfo(data) {
  return request({
    url: '/video/seriesInfo',
    method: 'put',
    data: data
  })
}

// 删除我的视频系列
export function delMySeriesInfo(seriesId) {
  return request({
    url: '/video/seriesInfo/' + seriesId,
    method: 'delete'
  })
}
