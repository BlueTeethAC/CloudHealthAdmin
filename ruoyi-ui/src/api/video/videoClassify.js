import request from '@/utils/request'

// 查询视频课程分类列表
export function listVideoClassify(query) {
  return request({
    url: '/video/videoClassify/list',
    method: 'get',
    params: query
  })
}

// 查询视频课程分类详细
export function getVideoClassify(classifyId) {
  return request({
    url: '/video/videoClassify/' + classifyId,
    method: 'get'
  })
}

// 新增视频课程分类
export function addVideoClassify(data) {
  return request({
    url: '/video/videoClassify',
    method: 'post',
    data: data
  })
}

// 修改视频课程分类
export function updateVideoClassify(data) {
  return request({
    url: '/video/videoClassify',
    method: 'put',
    data: data
  })
}

// 删除视频课程分类
export function delVideoClassify(classifyId) {
  return request({
    url: '/video/videoClassify/' + classifyId,
    method: 'delete'
  })
}
