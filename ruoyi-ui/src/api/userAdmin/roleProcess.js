import request from '@/utils/request'

// 查询角色认证审核列表
export function listRoleProcess(query) {
  return request({
    url: '/userAdmin/roleProcess/list',
    method: 'get',
    params: query
  })
}

// 查询角色认证审核详细
export function getRoleProcess(id) {
  return request({
    url: '/userAdmin/roleProcess/' + id,
    method: 'get'
  })
}

// 新增角色认证审核
export function addRoleProcess(data) {
  return request({
    url: '/userAdmin/roleProcess',
    method: 'post',
    data: data
  })
}

// 修改角色认证审核
export function updateRoleProcess(data) {
  return request({
    url: '/userAdmin/roleProcess',
    method: 'put',
    data: data
  })
}

// 删除角色认证审核
export function delRoleProcess(id) {
  return request({
    url: '/userAdmin/roleProcess/' + id,
    method: 'delete'
  })
}



// 审核用户角色申报
export function processRoleApplication(data) {
  return request({
    url: '/userAdmin/roleProcess/process',
    method: 'put',
    data: data
  })
}

