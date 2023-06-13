package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default()

	r.GET("/ping/:id", func(c *gin.Context) {
		id := c.Param("id")
		name := c.DefaultQuery("name", "Guest")
		pwd := c.Query("pwd") // 是 c.Request.URL.Query().Get("lastname") 的简写

		c.JSON(200, gin.H{
			"message": "pong",
			"id":      id,
			"name":    name,
			"pwd":     pwd,
		})
	})

	r.POST("/ping", func(c *gin.Context) {
		msg := c.PostForm("msg")
		c.JSON(200, gin.H{
			"message": msg,
		})
	})

	r.DELETE("/ping/:id", func(c *gin.Context) {
		id := c.Param("id")
		c.JSON(200, gin.H{
			"message": "pong",
			"id":      id,
		})
	})

	r.PUT("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})

	err := r.Run(":8081")
	if err != nil {
		return
	} // listen and serve on 0.0.0.0:8080
}
