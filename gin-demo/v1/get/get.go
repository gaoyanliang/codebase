package main

import "github.com/gin-gonic/gin"

// GET 请求
func main() {
	r := gin.Default()

	r.GET("/ping/:id", func(c *gin.Context) {
		// c.Param 获取 uri 中的参数
		id := c.Param("id")
		// c.DefaultQuery c.Query 获取 http://localhost:8090/ping/123?user=matt&pwd=123
		name := c.DefaultQuery("name", "Guest")
		pwd := c.Query("pwd") // 是 c.Request.URL.Query().Get("lastname") 的简写

		c.JSON(200, gin.H{
			"message": "pong",
			"id":      id,
			"name":    name,
			"pwd":     pwd,
		})
	})

	err := r.Run(":8090")
	if err != nil {
		return
	} // listen and serve on 0.0.0.0:8080
}
