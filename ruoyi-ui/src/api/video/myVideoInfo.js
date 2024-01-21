import request from '@/utils/request'

// 查询我的课程视频列表
export function listMyVideoInfo(query) {
  return request({
    url: '/video/videoInfo/list',
    method: 'get',
    params: query
  })
}

// 查询我的课程视频详细
export function getMyVideoInfo(videoId) {
  return request({
    url: '/video/videoInfo/' + videoId,
    method: 'get'
  })
}

// 新增我的课程视频
export function addMyVideoInfo(data) {
  return request({
    url: '/video/videoInfo',
    method: 'post',
    data: data
  })
}

// 修改我的课程视频
export function updateMyVideoInfo(data) {
  return request({
    url: '/video/videoInfo',
    method: 'put',
    data: data
  })
}

// 删除我的课程视频
export function delMyVideoInfo(videoId) {
  return request({
    url: '/video/videoInfo/' + videoId,
    method: 'delete'
  })
}

// 判断按钮是否可用的方法
export function isDisableButton(status){
  if(status === 0) return false;
  return true;
}

