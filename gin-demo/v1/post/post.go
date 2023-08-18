package main

import "github.com/gin-gonic/gin"

// PUT & POST
func main() {
	r := gin.Default()

	r.POST("/ping", func(c *gin.Context) {
		// 获取 body 传参
		// msg := c.DefaultPostForm("msg", "message")
		msg := c.PostForm("msg")
		c.JSON(200, gin.H{
			"message": msg,
		})
	})

	r.PUT("/ping", func(c *gin.Context) {
		// 获取 body 传参
		// msg := c.DefaultPostForm("msg", "message")
		msg := c.PostForm("msg")
		c.JSON(200, gin.H{
			"message": msg,
		})
	})

	// DELETE 基本上和 GET 类似，不同的是 GET 无法通过body 获取参数，DELETE 可以
	r.DELETE("/ping/:id", func(c *gin.Context) {
		id := c.Param("id")
		c.JSON(200, gin.H{
			"message": "pong",
			"id":      id,
		})
	})

	err := r.Run(":8090")
	if err != nil {
		return
	} // listen and serve on 0.0.0.0:8080
}
