// StringSender.sol
pragma solidity ^0.4.24;

contract StringSender {
    // 定义一个事件来记录发送字符串的操作
    event StringSent(address indexed sender, address indexed recipient, string message);

    // 该函数允许用户向指定账户发送自定义字符串
    // _recipient 是接收字符串的账户地址
    // _customString 是用户想要发送的自定义字符串
    function sendString(address _recipient, string _customString) public {
        // 检查接收者地址是否有效
        require(_recipient != address(0), "Invalid recipient address");

        // 调用者向接收者发送自定义字符串
        // 在这里我们只是将字符串与事件一起发送，实际上并没有进行代币转账
        // 如果需要发送代币，可以使用 transfer 或 send 函数
        _recipient.transfer(msg.value); // 假设这里msg.value已经包含了需要发送的代币数量

        // 发送事件记录
        emit StringSent(msg.sender, _recipient, _customString);
    }
}