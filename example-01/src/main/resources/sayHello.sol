// SPDX-License-Identifier: MIT
pragma solidity>=0.4.24 <0.6.11;

contract sayHello {
    // 返回 "hello" + 输入的字符串
    function greet(string memory name) public pure returns (string memory) {
        return string(abi.encodePacked("hello ", name));
    }
}
