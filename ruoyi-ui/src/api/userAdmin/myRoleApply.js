import request from '@/utils/request'

// 查询角色申报列表
export function listMyRoleApply(query) {
  return request({
    url: '/userAdmin/roleProcess/list',
    method: 'get',
    params: query
  })
}

// 查询角色申报详细
export function getMyRoleApply(id) {
  return request({
    url: '/userAdmin/roleProcess/' + id,
    method: 'get'
  })
}

// 新增角色申报
export function addMyRoleApply(data) {
  return request({
    url: '/userAdmin/roleProcess',
    method: 'post',
    data: data
  })
}

// 修改角色申报
export function updateMyRoleApply(data) {
  return request({
    url: '/userAdmin/roleProcess',
    method: 'put',
    data: data
  })
}

// 删除角色申报
export function delMyRoleApply(id) {
  return request({
    url: '/userAdmin/roleProcess/' + id,
    method: 'delete'
  })
}
