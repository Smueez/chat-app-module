# chat-app-module

This module required 4 string while calling this from another application via intent extras.

Current User ID -> UserID

Current User Name -> UserName

Yout applicaiton package name - AppPackageName

Massage UID of of the conversation beteen the current user and the other ends user or group -> MassageID(optional)

if it seems there were no previously conversation beteen the users then "MassageID" wont be naccesary.

the module itself will retrive a string from intent extras.

Massage UID -> MassageID

if there were previously recorder conversation between the users then this string can be ignored.
