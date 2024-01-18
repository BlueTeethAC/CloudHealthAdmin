import request from '@/utils/request'

// 查询角色权限列表
export function listRoleinfo(query) {
  return request({
    url: '/userAdmin/roleinfo/list',
    method: 'get',
    params: query
  })
}

// 查询角色权限详细
export function getRoleinfo(infoId) {
  return request({
    url: '/userAdmin/roleinfo/' + infoId,
    method: 'get'
  })
}

// 新增角色权限
export function addRoleinfo(data) {
  return request({
    url: '/userAdmin/roleinfo',
    method: 'post',
    data: data
  })
}

// 修改角色权限
export function updateRoleinfo(data) {
  return request({
    url: '/userAdmin/roleinfo',
    method: 'put',
    data: data
  })
}

// 删除角色权限
export function delRoleinfo(infoId) {
  return request({
    url: '/userAdmin/roleinfo/' + infoId,
    method: 'delete'
  })
}
