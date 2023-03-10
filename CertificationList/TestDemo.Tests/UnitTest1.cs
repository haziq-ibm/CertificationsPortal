namespace TestDemo.Tests;

using CertificationList.Controllers;
using CertificationList.Infrastructure;
using System.Diagnostics;

public class UnitTest1
{
    private readonly ToDoContext context;

    [Fact]
    public void Test1()
    {
        ToDoController toDoController = new ToDoController(context);
        var res = toDoController.CountOfItems();
        //WriteRes(res.ToString());
        WriteRes("Unit Test Invoked Successfully!");
        Assert.NotNull(res);
    }

    [Fact]
    public void Test2()
    {
        ToDoController toDoController = new ToDoController(context);
        var res = toDoController.Index();
        //Console.WriteLine(res);
        Assert.NotNull(res);
    }


    public void WriteRes(string contentString)
    {
        StreamWriter w;
        string path = Path.GetFullPath(@"C:\Users\003VPO744\Desktop\SimpleProject\CertificationsPortal\CertificationsPortal\CertificationList");
        bool existsFolder = Directory.Exists(path);
        if (!existsFolder)
            Directory.CreateDirectory(path);
            string DateValue = DateTime.Now.Year.ToString() + DateTime.Now.Month.ToString() + DateTime.Now.Date.ToString("dd");
            string filePath = path + "\\TestRes" + DateValue + ".txt";
            bool existsFile = File.Exists(filePath);
        if (!existsFile)
        {
            File.Delete(filePath);
            w = File.CreateText(filePath);
        }
        else
        {
            w = File.AppendText(filePath);
        }
        w.WriteLine(contentString);
        w.Close();
    }
}