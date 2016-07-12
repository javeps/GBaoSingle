do
  ArrayList = luanet.import_type('System.Collections.ArrayList');
  Hashtable = luanet.import_type('System.Collections.Hashtable');

  BuilderGBaoSng = {

    NBool = {
      _CID = "NBool";
      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.val = dataMap["val"];  -- [bool]
        return r;
      end;

    };

    NBytes = {
      _CID = "NBytes";
      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.buff = dataMap["buff"];  -- [byte[]]
        return r;
      end;

    };

    NChat = {
      _CID = "NChat";
      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.type = dataMap["type"];  -- 聊天类型 [int]
        r.fpcid = dataMap["fpcid"];  -- 说话人标识 [int]
        r.fpname = dataMap["fpname"];  -- 说话人名称 [string]
        r.content = dataMap["content"];  -- 说话内容 [string]
        r.creattime = dataMap["creattime"];  -- 创建时间long [long]
        r.creattimeStr = dataMap["creattimeStr"];  -- 创建时间Str [string]
        return r;
      end;

    };

    NChats = {
      _CID = "NChats";
      maps_list = function(maps) 
        if(maps == nil) then
          return ArrayList();
        end;
        local len = maps.Count
        local r = ArrayList();
        for i=0,len-1 do
          local _e = maps[i];
          local e = BuilderGBaoSng.NChat.parse(_e);
          if(e ~= nil) then
            r:Add(e);
          end
        end
        return r;
      end;

      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.list = BuilderGBaoSng.NChats.maps_list(dataMap["list"]);--list
        return r;
      end;

    };

    NEmail = {
      _CID = "NEmail";
      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.title = dataMap["title"];  -- 标题 [string]
        r.cont = dataMap["cont"];  -- 内容 [string]
        r.isRead = dataMap["isRead"];  -- 是否已读 [bool]
        r.isReceive = dataMap["isReceive"];  -- 是否已经领取奖励 [bool]
        r.awardJson = dataMap["awardJson"];  -- 奖励[{tpGet,tpId,tpVal}] [string]
        r.creattime = dataMap["creattime"];  -- 创建时间long [long]
        r.validtime = dataMap["validtime"];  -- 有效时间long [long]
        return r;
      end;

    };

    NEmails = {
      _CID = "NEmails";
      maps_list = function(maps) 
        if(maps == nil) then
          return ArrayList();
        end;
        local len = maps.Count
        local r = ArrayList();
        for i=0,len-1 do
          local _e = maps[i];
          local e = BuilderGBaoSng.NEmail.parse(_e);
          if(e ~= nil) then
            r:Add(e);
          end
        end
        return r;
      end;

      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.list = BuilderGBaoSng.NEmails.maps_list(dataMap["list"]);--list
        return r;
      end;

    };

    NRank = {
      _CID = "NRank";
      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.index = dataMap["index"];  -- 排名 [int]
        r.type = dataMap["type"];  -- 1sword,2wheel [int]
        r.unqid = dataMap["unqid"];  -- 登录唯一标识 [string]
        r.pname = dataMap["pname"];  -- 角色名 [string]
        r.sword = dataMap["sword"];  -- 战斗力 [int]
        r.wheel = dataMap["wheel"];  -- 无尽循环最大次数 [int]
        return r;
      end;

    };

    NRanks = {
      _CID = "NRanks";
      maps_list = function(maps) 
        if(maps == nil) then
          return ArrayList();
        end;
        local len = maps.Count
        local r = ArrayList();
        for i=0,len-1 do
          local _e = maps[i];
          local e = BuilderGBaoSng.NRank.parse(_e);
          if(e ~= nil) then
            r:Add(e);
          end
        end
        return r;
      end;

      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.list = BuilderGBaoSng.NRanks.maps_list(dataMap["list"]);--list
        return r;
      end;

    };

    NStr = {
      _CID = "NStr";
      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.val = dataMap["val"];  -- [string]
        return r;
      end;

    };

    ReturnStatus = {
      _CID = "ReturnStatus";
      parse = function(dataMap)
        if(dataMap == nil) then return nil; end;

        local r = Hashtable();
        r.succ = dataMap["succ"];  -- [int]
        r.msg = dataMap["msg"];  -- [string]
        return r;
      end;

    };

    callNet = { 
      __sessionid = 0;

      -- 提交错误问题
      error4Lua = function(uuid, device, error)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "error4Lua");-- cmd:error4Lua
        _map:Add("uuid", uuid);
        _map:Add("device", device);
        _map:Add("error", error);
        return _map;
      end;

      -- 取得聊天内容集合
      getChatsByHttp = function(unqid)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "getChatsByHttp");-- cmd:getChatsByHttp
        _map:Add("unqid", unqid);
        return _map;
      end;

      -- 取得邮件列表
      getNEmals = function(unqid, lasttime)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "getNEmals");-- cmd:getNEmals
        _map:Add("unqid", unqid);
        _map:Add("lasttime", lasttime);
        return _map;
      end;

      -- 取得排行榜列表
      getNRanks = function(unqid, type)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "getNRanks");-- cmd:getNRanks
        _map:Add("unqid", unqid);
        _map:Add("type", type);
        return _map;
      end;

      -- 判断是否初始化
      isInitSngByHttp = function(unqid, uuid)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "isInitSngByHttp");-- cmd:isInitSngByHttp
        _map:Add("unqid", unqid);
        _map:Add("uuid", uuid);
        return _map;
      end;

      -- 记录电话号码
      recordPhone = function(unqid, uuid, phone)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "recordPhone");-- cmd:recordPhone
        _map:Add("unqid", unqid);
        _map:Add("uuid", uuid);
        _map:Add("phone", phone);
        return _map;
      end;

      -- 随机取聊天名
      rndPnameByHttp = function()
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "rndPnameByHttp");-- cmd:rndPnameByHttp
        return _map;
      end;

      -- 发送聊天
      sendChatByHttp = function(unqid, cont)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "sendChatByHttp");-- cmd:sendChatByHttp
        _map:Add("unqid", unqid);
        _map:Add("cont", cont);
        return _map;
      end;

      -- 设置聊天名
      setPnameByHttp = function(unqid, newName)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "setPnameByHttp");-- cmd:setPnameByHttp
        _map:Add("unqid", unqid);
        _map:Add("newName", newName);
        return _map;
      end;

      -- 同步数据到服务器
      sync2Game = function(unqid, uuid, btPl, btHero, btPart, btProp, btNpc, btEmail, chnStr, chnSub, fight4hero, fight4part, npcStars)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "sync2Game");-- cmd:sync2Game
        _map:Add("unqid", unqid);
        _map:Add("uuid", uuid);
        _map:Add("btPl", btPl);
        _map:Add("btHero", btHero);
        _map:Add("btPart", btPart);
        _map:Add("btProp", btProp);
        _map:Add("btNpc", btNpc);
        _map:Add("btEmail", btEmail);
        _map:Add("chnStr", chnStr);
        _map:Add("chnSub", chnSub);
        _map:Add("fight4hero", fight4hero);
        _map:Add("fight4part", fight4part);
        _map:Add("npcStars", npcStars);
        return _map;
      end;

      -- 同步数据到本地
      sync2Local = function(unqid, uuid)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "sync2Local");-- cmd:sync2Local
        _map:Add("unqid", unqid);
        _map:Add("uuid", uuid);
        return _map;
      end;

      -- 国庆7天签到的验证
      verifySign4GuoQin = function(dayIn)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "verifySign4GuoQin");-- cmd:verifySign4GuoQin
        _map:Add("dayIn", dayIn);
        return _map;
      end;

      -- 判断是否是当天
      verifySytTime = function(timeStr)
        local _map = Hashtable();
        _map:Add("-100", BuilderGBaoSng.callNet.__sessionid);-- __sessionid
        _map:Add("cmd", "verifySytTime");-- cmd:verifySytTime
        _map:Add("timeStr", timeStr);
        return _map;
      end;


    };

    --[[
    // //////////////////////////////////////////////
    // 请求回掉分发解析
    // //////////////////////////////////////////////
    --]]

    onCallNet = { 

      --[[
      // //////////////////////////////////////////////
      // 逻辑分发
      // //////////////////////////////////////////////
      --]]

      disp = function(map)
        local cmd = map["cmd"];
        BuilderGBaoSng.onCallNet.disp_each(cmd, map);
      end;

      disp_each = function(cmd,map)
        local funMap = BuilderGBaoSng_switch[cmd];
        if(funMap ~= nil) then
          local exFun = funMap["fun"];
          local exMethod = funMap["name"];
          exFun(exMethod, map);
        end;

      end;


      --[[
      // //////////////////////////////////////////////
      // 参数解析
      // //////////////////////////////////////////////
      --]]

      -- 提交错误问题
      __onCallback_error4Lua = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);

        cllNetDis.dispatch(cmd,rst);
      end;

      -- 取得聊天内容集合
      __onCallback_getChatsByHttp = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nchats = BuilderGBaoSng.NChats.parse(map["nchats"]);

        cllNetDis.dispatch(cmd,nchats, rst);
      end;

      -- 取得邮件列表
      __onCallback_getNEmals = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nemails = BuilderGBaoSng.NEmails.parse(map["nemails"]);

        cllNetDis.dispatch(cmd,nemails, rst);
      end;

      -- 取得排行榜列表
      __onCallback_getNRanks = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nrnkSelf = BuilderGBaoSng.NRank.parse(map["nrnkSelf"]);
        local nrnks = BuilderGBaoSng.NRanks.parse(map["nrnks"]);

        cllNetDis.dispatch(cmd,nrnkSelf, nrnks, rst);
      end;

      -- 判断是否初始化
      __onCallback_isInitSngByHttp = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nbl = BuilderGBaoSng.NBool.parse(map["nbl"]);
        local nname = BuilderGBaoSng.NStr.parse(map["nname"]);

        cllNetDis.dispatch(cmd,nbl, nname, rst);
      end;

      -- 记录电话号码
      __onCallback_recordPhone = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);

        cllNetDis.dispatch(cmd,rst);
      end;

      -- 随机取聊天名
      __onCallback_rndPnameByHttp = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nrndName = BuilderGBaoSng.NStr.parse(map["nrndName"]);

        cllNetDis.dispatch(cmd,nrndName, rst);
      end;

      -- 发送聊天
      __onCallback_sendChatByHttp = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nchats = BuilderGBaoSng.NChats.parse(map["nchats"]);

        cllNetDis.dispatch(cmd,nchats, rst);
      end;

      -- 设置聊天名
      __onCallback_setPnameByHttp = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);

        cllNetDis.dispatch(cmd,rst);
      end;

      -- 同步数据到服务器
      __onCallback_sync2Game = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);

        cllNetDis.dispatch(cmd,rst);
      end;

      -- 同步数据到本地
      __onCallback_sync2Local = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nbtPl = BuilderGBaoSng.NBytes.parse(map["nbtPl"]);
        local nbtHero = BuilderGBaoSng.NBytes.parse(map["nbtHero"]);
        local nbtPart = BuilderGBaoSng.NBytes.parse(map["nbtPart"]);
        local nbtProp = BuilderGBaoSng.NBytes.parse(map["nbtProp"]);
        local nbtNpc = BuilderGBaoSng.NBytes.parse(map["nbtNpc"]);
        local nbtEmail = BuilderGBaoSng.NBytes.parse(map["nbtEmail"]);

        cllNetDis.dispatch(cmd,nbtPl, nbtHero, nbtPart, nbtProp, nbtNpc, nbtEmail, rst);
      end;

      -- 国庆7天签到的验证
      __onCallback_verifySign4GuoQin = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nbl = BuilderGBaoSng.NBool.parse(map["nbl"]);

        cllNetDis.dispatch(cmd,nbl, rst);
      end;

      -- 判断是否是当天
      __onCallback_verifySytTime = function(cmd,map)
        local retVal = map["1"];
        local rst = BuilderGBaoSng.ReturnStatus.parse(retVal);
        local nbl = BuilderGBaoSng.NBool.parse(map["nbl"]);

        cllNetDis.dispatch(cmd,nbl, rst);
      end;

    };

  };

  --[[
  // //////////////////////////////////////////////
  // 请求回掉分发解析所对应的对象
  // //////////////////////////////////////////////
  --]]

  BuilderGBaoSng_switch = {
    ["error4Lua"] = {["name"] = "error4Lua"; fun = BuilderGBaoSng.onCallNet.__onCallback_error4Lua}; --提交错误问题
    ["getChatsByHttp"] = {["name"] = "getChatsByHttp"; fun = BuilderGBaoSng.onCallNet.__onCallback_getChatsByHttp}; --取得聊天内容集合
    ["getNEmals"] = {["name"] = "getNEmals"; fun = BuilderGBaoSng.onCallNet.__onCallback_getNEmals}; --取得邮件列表
    ["getNRanks"] = {["name"] = "getNRanks"; fun = BuilderGBaoSng.onCallNet.__onCallback_getNRanks}; --取得排行榜列表
    ["isInitSngByHttp"] = {["name"] = "isInitSngByHttp"; fun = BuilderGBaoSng.onCallNet.__onCallback_isInitSngByHttp}; --判断是否初始化
    ["recordPhone"] = {["name"] = "recordPhone"; fun = BuilderGBaoSng.onCallNet.__onCallback_recordPhone}; --记录电话号码
    ["rndPnameByHttp"] = {["name"] = "rndPnameByHttp"; fun = BuilderGBaoSng.onCallNet.__onCallback_rndPnameByHttp}; --随机取聊天名
    ["sendChatByHttp"] = {["name"] = "sendChatByHttp"; fun = BuilderGBaoSng.onCallNet.__onCallback_sendChatByHttp}; --发送聊天
    ["setPnameByHttp"] = {["name"] = "setPnameByHttp"; fun = BuilderGBaoSng.onCallNet.__onCallback_setPnameByHttp}; --设置聊天名
    ["sync2Game"] = {["name"] = "sync2Game"; fun = BuilderGBaoSng.onCallNet.__onCallback_sync2Game}; --同步数据到服务器
    ["sync2Local"] = {["name"] = "sync2Local"; fun = BuilderGBaoSng.onCallNet.__onCallback_sync2Local}; --同步数据到本地
    ["verifySign4GuoQin"] = {["name"] = "verifySign4GuoQin"; fun = BuilderGBaoSng.onCallNet.__onCallback_verifySign4GuoQin}; --国庆7天签到的验证
    ["verifySytTime"] = {["name"] = "verifySytTime"; fun = BuilderGBaoSng.onCallNet.__onCallback_verifySytTime}; --判断是否是当天
  };

end;

module("BuilderGBaoSng",package.seeall)
