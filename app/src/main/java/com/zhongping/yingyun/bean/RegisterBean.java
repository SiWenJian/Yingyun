package com.zhongping.yingyun.bean;

import java.util.List;

/**
 * Created by Wenjian on 2019/4/24.
 */

public class RegisterBean {

    /**
     * resultCode : 1
     * resultDesc : 注册成功
     * resultData : null
     * total : 0
     * pageNumber : 0
     * rows : null
     * object : {"id":"40289b986a4df7df016a4e37a01f0002","name":"张飞","loginName":"18860230296","password":"$2a$10$lJWNZGr1abA9xf.BKWltQexWGRkwznQ.iuudhoetV9vIo9YuYjtz2","mobile":"18860230296","authState":"0","state":"ENABLE","type":"3","roleType":"0","createTime":"2019-04-24 15:20:29","delFlag":"0","roles":[{"id":"402898555f675066015f676a99ec000a","code":"gerenrenzheng","name":"个人认证","state":"ENABLE","description":"个人认证","delFlag":"0","authorities":[{"id":"402898435f38da5d015f38dbdb720001","name":"个人中心","code":"GRZX","icon":"","router":"","parentId":"40289b14661a15cc01661a728fe9006d","sortNo":36,"moduleType":"MENU","clickEvent":""},{"id":"40289b14661a15cc01661a728fe9006d","name":"用户资质","code":"YHZZ","icon":"","router":"","parentId":"-1","sortNo":8,"moduleType":"MENU","clickEvent":""},{"id":"402898435f421121015f42183f910000","name":"个人认证","code":"GRRZ","icon":"","router":"/carrierEmployee/showPerson","parentId":"402898435f38da5d015f38dbdb720001","moduleType":"MENU","clickEvent":""}]}],"orgCode":"41-83-905","orgId":0}
     * sCount : 0
     * fCount : 0
     */

    private String resultCode;
    private String resultDesc;
    private Object resultData;
    private int total;
    private int pageNumber;
    private Object rows;
    private ObjectBean object;
    private int sCount;
    private int fCount;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public int getSCount() {
        return sCount;
    }

    public void setSCount(int sCount) {
        this.sCount = sCount;
    }

    public int getFCount() {
        return fCount;
    }

    public void setFCount(int fCount) {
        this.fCount = fCount;
    }

    public static class ObjectBean {
        /**
         * id : 40289b986a4df7df016a4e37a01f0002
         * name : 张飞
         * loginName : 18860230296
         * password : $2a$10$lJWNZGr1abA9xf.BKWltQexWGRkwznQ.iuudhoetV9vIo9YuYjtz2
         * mobile : 18860230296
         * authState : 0
         * state : ENABLE
         * type : 3
         * roleType : 0
         * createTime : 2019-04-24 15:20:29
         * delFlag : 0
         * roles : [{"id":"402898555f675066015f676a99ec000a","code":"gerenrenzheng","name":"个人认证","state":"ENABLE","description":"个人认证","delFlag":"0","authorities":[{"id":"402898435f38da5d015f38dbdb720001","name":"个人中心","code":"GRZX","icon":"","router":"","parentId":"40289b14661a15cc01661a728fe9006d","sortNo":36,"moduleType":"MENU","clickEvent":""},{"id":"40289b14661a15cc01661a728fe9006d","name":"用户资质","code":"YHZZ","icon":"","router":"","parentId":"-1","sortNo":8,"moduleType":"MENU","clickEvent":""},{"id":"402898435f421121015f42183f910000","name":"个人认证","code":"GRRZ","icon":"","router":"/carrierEmployee/showPerson","parentId":"402898435f38da5d015f38dbdb720001","moduleType":"MENU","clickEvent":""}]}]
         * orgCode : 41-83-905
         * orgId : 0
         */

        private String id;
        private String name;
        private String loginName;
        private String password;
        private String mobile;
        private String authState;
        private String state;
        private String type;
        private String roleType;
        private String createTime;
        private String delFlag;
        private String orgCode;
        private int orgId;
        private List<RolesBean> roles;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAuthState() {
            return authState;
        }

        public void setAuthState(String authState) {
            this.authState = authState;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRoleType() {
            return roleType;
        }

        public void setRoleType(String roleType) {
            this.roleType = roleType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public static class RolesBean {
            /**
             * id : 402898555f675066015f676a99ec000a
             * code : gerenrenzheng
             * name : 个人认证
             * state : ENABLE
             * description : 个人认证
             * delFlag : 0
             * authorities : [{"id":"402898435f38da5d015f38dbdb720001","name":"个人中心","code":"GRZX","icon":"","router":"","parentId":"40289b14661a15cc01661a728fe9006d","sortNo":36,"moduleType":"MENU","clickEvent":""},{"id":"40289b14661a15cc01661a728fe9006d","name":"用户资质","code":"YHZZ","icon":"","router":"","parentId":"-1","sortNo":8,"moduleType":"MENU","clickEvent":""},{"id":"402898435f421121015f42183f910000","name":"个人认证","code":"GRRZ","icon":"","router":"/carrierEmployee/showPerson","parentId":"402898435f38da5d015f38dbdb720001","moduleType":"MENU","clickEvent":""}]
             */

            private String id;
            private String code;
            private String name;
            private String state;
            private String description;
            private String delFlag;
            private List<AuthoritiesBean> authorities;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public List<AuthoritiesBean> getAuthorities() {
                return authorities;
            }

            public void setAuthorities(List<AuthoritiesBean> authorities) {
                this.authorities = authorities;
            }

            public static class AuthoritiesBean {
                /**
                 * id : 402898435f38da5d015f38dbdb720001
                 * name : 个人中心
                 * code : GRZX
                 * icon :
                 * router :
                 * parentId : 40289b14661a15cc01661a728fe9006d
                 * sortNo : 36
                 * moduleType : MENU
                 * clickEvent :
                 */

                private String id;
                private String name;
                private String code;
                private String icon;
                private String router;
                private String parentId;
                private int sortNo;
                private String moduleType;
                private String clickEvent;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getRouter() {
                    return router;
                }

                public void setRouter(String router) {
                    this.router = router;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public int getSortNo() {
                    return sortNo;
                }

                public void setSortNo(int sortNo) {
                    this.sortNo = sortNo;
                }

                public String getModuleType() {
                    return moduleType;
                }

                public void setModuleType(String moduleType) {
                    this.moduleType = moduleType;
                }

                public String getClickEvent() {
                    return clickEvent;
                }

                public void setClickEvent(String clickEvent) {
                    this.clickEvent = clickEvent;
                }
            }
        }
    }
}
