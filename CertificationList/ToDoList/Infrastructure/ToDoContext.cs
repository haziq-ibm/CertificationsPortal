using CertificationList.Models;
using Microsoft.EntityFrameworkCore;

namespace CertificationList.Infrastructure
{
    public class ToDoContext : DbContext
    {
        public ToDoContext(DbContextOptions<ToDoContext> options) : base(options)
        {
        }

        public DbSet<TodoList> ToDoList { get; set; }
    }
}
