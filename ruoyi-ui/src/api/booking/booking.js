import request from '@/utils/request'

// 查询私教，健身房预约列表
export function listBooking(query) {
  return request({
    url: '/booking/booking/list',
    method: 'get',
    params: query
  })
}

// 查询私教，健身房预约详细
export function getBooking(infoId) {
  return request({
    url: '/booking/booking/' + infoId,
    method: 'get'
  })
}

// 新增私教，健身房预约
export function addBooking(data) {
  return request({
    url: '/booking/booking',
    method: 'post',
    data: data
  })
}

// 修改私教，健身房预约
export function updateBooking(data) {
  return request({
    url: '/booking/booking',
    method: 'put',
    data: data
  })
}

// 删除私教，健身房预约
export function delBooking(infoId) {
  return request({
    url: '/booking/booking/' + infoId,
    method: 'delete'
  })
}
