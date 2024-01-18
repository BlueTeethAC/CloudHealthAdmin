import request from '@/utils/request'

// 查询线上课程视频列表
export function listVideoInfo(query) {
  return request({
    url: '/video/videoInfo/list',
    method: 'get',
    params: query
  })
}

// 查询线上课程视频详细
export function getVideoInfo(videoId) {
  return request({
    url: '/video/videoInfo/' + videoId,
    method: 'get'
  })
}

// 新增线上课程视频
export function addVideoInfo(data) {
  return request({
    url: '/video/videoInfo',
    method: 'post',
    data: data
  })
}

// 修改线上课程视频
export function updateVideoInfo(data) {
  return request({
    url: '/video/videoInfo',
    method: 'put',
    data: data
  })
}

// 删除线上课程视频
export function delVideoInfo(videoId) {
  return request({
    url: '/video/videoInfo/' + videoId,
    method: 'delete'
  })
}
