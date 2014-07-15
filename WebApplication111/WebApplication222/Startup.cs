using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(WebApplication222.Startup))]
namespace WebApplication222
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
