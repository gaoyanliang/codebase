package main

import "github.com/gin-gonic/gin"

// 浏览器访问：http://localhost:8090/ping，得到以下响应：
// {
// "success":pong,
// }
func main() {
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})
	r.Run(":8090") // 修改端口号, 默认8080
}
