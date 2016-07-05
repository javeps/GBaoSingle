do

   GBaoSngFixed = {

      PubAttr = {
         signAppKey = "gbarpg"; -- 密匙key
      };

      ResultStatus = {
         R_Success = 0; -- 成功
         R_Error = -999; -- 异常！
         R_NameLenthError = -199; -- 名字长度应为2~8个字符! 
         R_NameIsHas = -89; -- 角色名已存在! 
      };

   };

end;

module("GBaoSngFixed",package.seeall)
