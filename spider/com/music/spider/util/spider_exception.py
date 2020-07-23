class RequestException(Exception):
    """
    request时传回的status_code不为200
    """
    def __init__(self, url, msg="发送请求是出现连接错误！"):
        super(RequestException, self).__init__(msg + "\nurl: " + url)
        
class UninitException(Exception):
    """
    在执行过程中忘记初始化的异常
    """
    def __init__(self, name):
        super(UninitException, self).__init__(name + " 没有初始化！")
        